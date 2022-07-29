/**
 * Copyright 2010 The Apache Software Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hbase.regionserver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HBaseTestCase;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.ColumnCountGetFilter;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.regionserver.HRegion.RegionScanner;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Basic stand-alone testing of HRegion.
 *
 * A lot of the meta information for an HRegion now lives inside other
 * HRegions or in the HBaseMaster, so only basic testing is possible.
 */
public class TestHRegion extends HBaseTestCase {
  static final Log LOG = LogFactory.getLog(TestHRegion.class);

  HRegion region = null;
  private final String DIR = "test/build/data/TestHRegion/";

  private final int MAX_VERSIONS = 2;

  // Test names
  protected final byte[] tableName = Bytes.toBytes("testtable");;
  protected final byte[] qual1 = Bytes.toBytes("qual1");
  protected final byte[] qual2 = Bytes.toBytes("qual2");
  protected final byte[] qual3 = Bytes.toBytes("qual3");
  protected final byte[] value1 = Bytes.toBytes("value1");
  protected final byte[] value2 = Bytes.toBytes("value2");
  protected final byte [] row = Bytes.toBytes("rowA");

  

  //////////////////////////////////////////////////////////////////////////////
  // New tests that doesn't spin up a mini cluster but rather just test the
  // individual code pieces in the HRegion. Putting files locally in
  // /tmp/testtable
  //////////////////////////////////////////////////////////////////////////////


  private void deleteColumns(HRegion r, String value, String keyPrefix)
  throws IOException {
    InternalScanner scanner = buildScanner(keyPrefix, value, r);
    int count = 0;
    boolean more = false;
    List<KeyValue> results = new ArrayList<KeyValue>();
    do {
      more = scanner.next(results);
      if (results != null && !results.isEmpty())
        count++;
      else
        break;
      Delete delete = new Delete(results.get(0).getRow());
      delete.deleteColumn(Bytes.toBytes("trans-tags"), Bytes.toBytes("qual2"));
      r.delete(delete, null, false);
      results.clear();
    } while (more);
    assertEquals("Did not perform correct number of deletes", 3, count);
  }

  private int getNumberOfRows(String keyPrefix, String value, HRegion r) throws Exception {
    InternalScanner resultScanner = buildScanner(keyPrefix, value, r);
    int numberOfResults = 0;
    List<KeyValue> results = new ArrayList<KeyValue>();
    boolean more = false;
    do {
      more = resultScanner.next(results);
      if (results != null && !results.isEmpty()) numberOfResults++;
      else break;
      for (KeyValue kv: results) {
        System.out.println("kv=" + kv.toString() + ", " + Bytes.toString(kv.getValue()));
      }
      results.clear();
    } while(more);
    return numberOfResults;
  }

  private InternalScanner buildScanner(String keyPrefix, String value, HRegion r)
  throws IOException {
    // Defaults FilterList.Operator.MUST_PASS_ALL.
    FilterList allFilters = new FilterList();
    allFilters.addFilter(new PrefixFilter(Bytes.toBytes(keyPrefix)));
    // Only return rows where this column value exists in the row.
    SingleColumnValueFilter filter =
      new SingleColumnValueFilter(Bytes.toBytes("trans-tags"),
        Bytes.toBytes("qual2"), CompareOp.EQUAL, Bytes.toBytes(value));
    filter.setFilterIfMissing(true);
    allFilters.addFilter(filter);
    Scan scan = new Scan();
    scan.addFamily(Bytes.toBytes("trans-blob"));
    scan.addFamily(Bytes.toBytes("trans-type"));
    scan.addFamily(Bytes.toBytes("trans-date"));
    scan.addFamily(Bytes.toBytes("trans-tags"));
    scan.addFamily(Bytes.toBytes("trans-group"));
    scan.setFilter(allFilters);
    return r.getScanner(scan);
  }

  private void putRows(HRegion r, int numRows, String value, String key)
  throws IOException {
    for (int i = 0; i < numRows; i++) {
      String row = key + "_" + i/* UUID.randomUUID().toString() */;
      System.out.println(String.format("Saving row: %s, with value %s", row,
        value));
      Put put = new Put(Bytes.toBytes(row));
      put.add(Bytes.toBytes("trans-blob"), null,
        Bytes.toBytes("value for blob"));
      put.add(Bytes.toBytes("trans-type"), null, Bytes.toBytes("statement"));
      put.add(Bytes.toBytes("trans-date"), null,
        Bytes.toBytes("20090921010101999"));
      put.add(Bytes.toBytes("trans-tags"), Bytes.toBytes("qual2"),
        Bytes.toBytes(value));
      put.add(Bytes.toBytes("trans-group"), null,
        Bytes.toBytes("adhocTransactionGroupId"));
      r.put(put);
    }
  }

  public void testFamilyWithAndWithoutColon() throws Exception {
	byte[] b = Bytes.toBytes(getName());
	byte[] cf = Bytes.toBytes("cf");
	initHRegion(b, getName(), cf);
	Put p = new Put(b);
	byte[] cfwithcolon = Bytes.toBytes("cf:");
	p.add(cfwithcolon, cfwithcolon, cfwithcolon);
	boolean exception = false;
	try {
		this.region.put(p);
	} catch (NoSuchColumnFamilyException e) {
		exception = true;
	}
	assertTrue(exception);
}

  private void assertICV(byte [] row,
                         byte [] familiy,
                         byte[] qualifier,
                         long amount) throws IOException {
    // run a get and see?
    Get get = new Get(row);
    get.addColumn(familiy, qualifier);
    Result result = region.get(get, null);
    assertEquals(1, result.size());

    KeyValue kv = result.raw()[0];
    long r = Bytes.toLong(kv.getValue());
    assertEquals(amount, r);
  }



  protected class FlushThread extends Thread {
    private volatile boolean done;
    private Throwable error = null;

    public void done() {
      done = true;
      synchronized (this) {
        interrupt();
      }
    }

    public void checkNoError() {
      if (error != null) {
        Assert.assertNull(error);
      }
    }

    @Override
    public void run() {
      done = false;
      while (!done) {
        synchronized (this) {
          try {
            wait();
          } catch (InterruptedException ignored) {
            if (done) {
              break;
            }
          }
        }
        try {
          region.flushcache();
        } catch (IOException e) {
          if (!done) {
            LOG.error("Error while flusing cache", e);
            error = e;
          }
          break;
        }
      }

    }

    public void flush() {
      synchronized (this) {
        notify();
      }

    }
  }

  protected class PutThread extends Thread {
    private volatile boolean done;
    private Throwable error = null;
    private int numRows;
    private byte[][] families;
    private byte[][] qualifiers;

    private PutThread(int numRows, byte[][] families,
      byte[][] qualifiers) {
      this.numRows = numRows;
      this.families = families;
      this.qualifiers = qualifiers;
    }

    public void done() {
      done = true;
      synchronized (this) {
        interrupt();
      }
    }

    public void checkNoError() {
      if (error != null) {
        Assert.assertNull(error);
      }
    }

    @Override
    public void run() {
      done = false;
      int val = 0;
      while (!done) {
        try {
          for (int r = 0; r < numRows; r++) {
            byte[] row = Bytes.toBytes("row" + r);
            Put put = new Put(row);
            for (int f = 0; f < families.length; f++) {
              for (int q = 0; q < qualifiers.length; q++) {
                put.add(families[f], qualifiers[q], (long) val,
                  Bytes.toBytes(val));
              }
            }
            region.put(put);
            if (val > 0 && val % 47 == 0){
              //System.out.println("put iteration = " + val);
              Delete delete = new Delete(row, (long)val-30, null);
              region.delete(delete, null, true);
            }
            val++;
          }
        } catch (IOException e) {
          LOG.error("error while putting records", e);
          error = e;
          break;
        }
      }

    }

  }


  private void putData(int startRow, int numRows, byte [] qf,
      byte [] ...families)
  throws IOException {
    for(int i=startRow; i<startRow+numRows; i++) {
      Put put = new Put(Bytes.toBytes("" + i));
      for(byte [] family : families) {
        put.add(family, qf, null);
      }
      region.put(put);
    }
  }

  private void verifyData(HRegion newReg, int startRow, int numRows, byte [] qf,
      byte [] ... families)
  throws IOException {
    for(int i=startRow; i<startRow + numRows; i++) {
      byte [] row = Bytes.toBytes("" + i);
      Get get = new Get(row);
      for(byte [] family : families) {
        get.addColumn(family, qf);
      }
      Result result = newReg.get(get, null);
      KeyValue [] raw = result.sorted();
      assertEquals(families.length, result.size());
      for(int j=0; j<families.length; j++) {
        assertEquals(0, Bytes.compareTo(row, raw[j].getRow()));
        assertEquals(0, Bytes.compareTo(families[j], raw[j].getFamily()));
        assertEquals(0, Bytes.compareTo(qf, raw[j].getQualifier()));
      }
    }
  }

  private void assertGet(final HRegion r, final byte [] family, final byte [] k)
  throws IOException {
    // Now I have k, get values out and assert they are as expected.
    Get get = new Get(k).addFamily(family).setMaxVersions();
    KeyValue [] results = r.get(get, null).raw();
    for (int j = 0; j < results.length; j++) {
      byte [] tmp = results[j].getValue();
      // Row should be equal to value every time.
      assertTrue(Bytes.equals(k, tmp));
    }
  }

  /*
   * Assert first value in the passed region is <code>firstValue</code>.
   * @param r
   * @param fs
   * @param firstValue
   * @throws IOException
   */
  private void assertScan(final HRegion r, final byte [] fs,
      final byte [] firstValue)
  throws IOException {
    byte [][] families = {fs};
    Scan scan = new Scan();
    for (int i = 0; i < families.length; i++) scan.addFamily(families[i]);
    InternalScanner s = r.getScanner(scan);
    try {
      List<KeyValue> curVals = new ArrayList<KeyValue>();
      boolean first = true;
      OUTER_LOOP: while(s.next(curVals)) {
        for (KeyValue kv: curVals) {
          byte [] val = kv.getValue();
          byte [] curval = val;
          if (first) {
            first = false;
            assertTrue(Bytes.compareTo(curval, firstValue) == 0);
          } else {
            // Not asserting anything.  Might as well break.
            break OUTER_LOOP;
          }
        }
      }
    } finally {
      s.close();
    }
  }

  protected HRegion [] split(final HRegion r, final byte [] splitRow)
  throws IOException {
    // Assert can get mid key from passed region.
    assertGet(r, fam3, splitRow);
    HRegion [] regions = r.splitRegion(splitRow);
    assertEquals(regions.length, 2);
    return regions;
  }

  private HBaseConfiguration initSplit() {
    HBaseConfiguration conf = new HBaseConfiguration();
    // Always compact if there is more than one store file.
    conf.setInt("hbase.hstore.compactionThreshold", 2);

    // Make lease timeout longer, lease checks less frequent
    conf.setInt("hbase.master.lease.thread.wakefrequency", 5 * 1000);

    conf.setInt(HConstants.HBASE_REGIONSERVER_LEASE_PERIOD_KEY, 10 * 1000);

    // Increase the amount of time between client retries
    conf.setLong("hbase.client.pause", 15 * 1000);

    // This size should make it so we always split using the addContent
    // below.  After adding all data, the first region is 1.3M
    conf.setLong("hbase.hregion.max.filesize", 1024 * 128);
    return conf;
  }

  private void initHRegion (byte [] tableName, String callingMethod,
    byte[] ... families)
  throws IOException {
    initHRegion(tableName, callingMethod, new HBaseConfiguration(), families);
  }

  private void initHRegion (byte [] tableName, String callingMethod,
    HBaseConfiguration conf, byte [] ... families)
  throws IOException{
    HTableDescriptor htd = new HTableDescriptor(tableName);
    for(byte [] family : families) {
      htd.addFamily(new HColumnDescriptor(family));
    }
    HRegionInfo info = new HRegionInfo(htd, null, null, false);
    Path path = new Path(DIR + callingMethod);
    region = HRegion.createHRegion(info, path, conf);
  }
}
