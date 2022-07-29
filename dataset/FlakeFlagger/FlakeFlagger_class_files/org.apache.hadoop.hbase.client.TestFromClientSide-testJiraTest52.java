/**
 * Copyright 2009 The Apache Software Foundation
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
package org.apache.hadoop.hbase.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HServerAddress;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.WhileMatchFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Run tests that use the HBase clients; {@link HTable} and {@link HTablePool}.
 * Sets up the HBase mini cluster once at start and runs through all client tests.
 * Each creates a table named for the method and does its stuff against that.
 */
public class TestFromClientSide {
  final Log LOG = LogFactory.getLog(getClass());
  private final static HBaseTestingUtility TEST_UTIL = new HBaseTestingUtility();
  private static byte [] ROW = Bytes.toBytes("testRow");
  private static byte [] FAMILY = Bytes.toBytes("testFamily");
  private static byte [] QUALIFIER = Bytes.toBytes("testQualifier");
  private static byte [] VALUE = Bytes.toBytes("testValue");

  /**
   * @throws java.lang.Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    TEST_UTIL.startMiniCluster(3);
  }

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    // Nothing to do.
  }

  private void deleteColumns(HTable ht, String value, String keyPrefix)
  throws IOException {
    ResultScanner scanner = buildScanner(keyPrefix, value, ht);
    Iterator<Result> it = scanner.iterator();
    int count = 0;
    while (it.hasNext()) {
      Result result = it.next();
      Delete delete = new Delete(result.getRow());
      delete.deleteColumn(Bytes.toBytes("trans-tags"), Bytes.toBytes("qual2"));
      ht.delete(delete);
      count++;
    }
    assertEquals("Did not perform correct number of deletes", 3, count);
  }

  private int getNumberOfRows(String keyPrefix, String value, HTable ht)
      throws Exception {
    ResultScanner resultScanner = buildScanner(keyPrefix, value, ht);
    Iterator<Result> scanner = resultScanner.iterator();
    int numberOfResults = 0;
    while (scanner.hasNext()) {
      Result result = scanner.next();
      System.out.println("Got back key: " + Bytes.toString(result.getRow()));
      for (KeyValue kv : result.raw()) {
        System.out.println("kv=" + kv.toString() + ", "
            + Bytes.toString(kv.getValue()));
      }
      numberOfResults++;
    }
    return numberOfResults;
  }

  private ResultScanner buildScanner(String keyPrefix, String value, HTable ht)
      throws IOException {
    // OurFilterList allFilters = new OurFilterList();
    FilterList allFilters = new FilterList(/* FilterList.Operator.MUST_PASS_ALL */);
    allFilters.addFilter(new PrefixFilter(Bytes.toBytes(keyPrefix)));
    SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes
        .toBytes("trans-tags"), Bytes.toBytes("qual2"), CompareOp.EQUAL, Bytes
        .toBytes(value));
    filter.setFilterIfMissing(true);
    allFilters.addFilter(filter);

    // allFilters.addFilter(new
    // RowExcludingSingleColumnValueFilter(Bytes.toBytes("trans-tags"),
    // Bytes.toBytes("qual2"), CompareOp.EQUAL, Bytes.toBytes(value)));

    Scan scan = new Scan();
    scan.addFamily(Bytes.toBytes("trans-blob"));
    scan.addFamily(Bytes.toBytes("trans-type"));
    scan.addFamily(Bytes.toBytes("trans-date"));
    scan.addFamily(Bytes.toBytes("trans-tags"));
    scan.addFamily(Bytes.toBytes("trans-group"));
    scan.setFilter(allFilters);

    return ht.getScanner(scan);
  }

  private void putRows(HTable ht, int numRows, String value, String key)
      throws IOException {
    for (int i = 0; i < numRows; i++) {
      String row = key + "_" + UUID.randomUUID().toString();
      System.out.println(String.format("Saving row: %s, with value %s", row,
          value));
      Put put = new Put(Bytes.toBytes(row));
      put.add(Bytes.toBytes("trans-blob"), null, Bytes
          .toBytes("value for blob"));
      put.add(Bytes.toBytes("trans-type"), null, Bytes.toBytes("statement"));
      put.add(Bytes.toBytes("trans-date"), null, Bytes
          .toBytes("20090921010101999"));
      put.add(Bytes.toBytes("trans-tags"), Bytes.toBytes("qual2"), Bytes
          .toBytes(value));
      put.add(Bytes.toBytes("trans-group"), null, Bytes
          .toBytes("adhocTransactionGroupId"));
      ht.put(put);
    }
  }

  /*
   * @param key
   * @return Scan with RowFilter that does LESS than passed key.
   */
  private Scan createScanWithRowFilter(final byte [] key) {
    return createScanWithRowFilter(key, null, CompareFilter.CompareOp.LESS);
  }

  /*
   * @param key
   * @param op
   * @param startRow
   * @return Scan with RowFilter that does CompareOp op on passed key.
   */
  private Scan createScanWithRowFilter(final byte [] key,
      final byte [] startRow, CompareFilter.CompareOp op) {
    // Make sure key is of some substance... non-null and > than first key.
    assertTrue(key != null && key.length > 0 &&
      Bytes.BYTES_COMPARATOR.compare(key, new byte [] {'a', 'a', 'a'}) >= 0);
    LOG.info("Key=" + Bytes.toString(key));
    Scan s = startRow == null? new Scan(): new Scan(startRow);
    Filter f = new RowFilter(op, new BinaryComparator(key));
    f = new WhileMatchFilter(f);
    s.setFilter(f);
    return s;
  }

  /*
   * @param t
   * @param s
   * @return Count of rows in table.
   * @throws IOException
   */
  private int countRows(final HTable t, final Scan s)
  throws IOException {
    // Assert all rows in table.
    ResultScanner scanner = t.getScanner(s);
    int count = 0;
    for (Result result: scanner) {
      count++;
      assertTrue(result.size() > 0);
      // LOG.info("Count=" + count + ", row=" + Bytes.toString(result.getRow()));
    }
    return count;
  }

  private void assertRowCount(final HTable t, final int expected)
  throws IOException {
    assertEquals(expected, countRows(t, new Scan()));
  }

  /*
   * Split table into multiple regions.
   * @param t Table to split.
   * @return Map of regions to servers.
   * @throws IOException
   */
  private Map<HRegionInfo, HServerAddress> splitTable(final HTable t)
  throws IOException {
    // Split this table in two.
    HBaseAdmin admin = new HBaseAdmin(TEST_UTIL.getConfiguration());
    admin.split(t.getTableName());
    Map<HRegionInfo, HServerAddress> regions = waitOnSplit(t);
    assertTrue(regions.size() > 1);
    return regions;
  }

  /*
   * Wait on table split.  May return because we waited long enough on the split
   * and it didn't happen.  Caller should check.
   * @param t
   * @return Map of table regions; caller needs to check table actually split.
   */
  private Map<HRegionInfo, HServerAddress> waitOnSplit(final HTable t)
  throws IOException {
    Map<HRegionInfo, HServerAddress> regions = t.getRegionsInfo();
    int originalCount = regions.size();
    for (int i = 0; i < TEST_UTIL.getConfiguration().getInt("hbase.test.retries", 30); i++) {
      Thread.currentThread();
      try {
        Thread.sleep(TEST_UTIL.getConfiguration().getInt("hbase.server.thread.wakefrequency", 1000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      regions = t.getRegionsInfo();
      if (regions.size() > originalCount) break;
    }
    return regions;
  }

  

  

  //
  // JIRA Testers
  //

  /**
 * HBASE-52 Add a means of scanning over all versions
 */@Test public void testJiraTest52() throws Exception{byte[] TABLE=Bytes.toBytes("testJiraTest52");byte[][] VALUES=makeNAscii(VALUE,7);long[] STAMPS=makeStamps(7);HTable ht=TEST_UTIL.createTable(TABLE,FAMILY,10);Put put=new Put(ROW);put.add(FAMILY,QUALIFIER,STAMPS[0],VALUES[0]);put.add(FAMILY,QUALIFIER,STAMPS[1],VALUES[1]);put.add(FAMILY,QUALIFIER,STAMPS[2],VALUES[2]);put.add(FAMILY,QUALIFIER,STAMPS[3],VALUES[3]);put.add(FAMILY,QUALIFIER,STAMPS[4],VALUES[4]);put.add(FAMILY,QUALIFIER,STAMPS[5],VALUES[5]);ht.put(put);getAllVersionsAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,0,5);scanAllVersionsAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,0,5);TEST_UTIL.flush();getAllVersionsAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,0,5);scanAllVersionsAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,0,5);}

  //
  // Bulk Testers
  //

  private void getVersionRangeAndVerifyGreaterThan(HTable ht, byte [] row,
      byte [] family, byte [] qualifier, long [] stamps, byte [][] values,
      int start, int end)
  throws IOException {
    Get get = new Get(row);
    get.addColumn(family, qualifier);
    get.setMaxVersions(Integer.MAX_VALUE);
    get.setTimeRange(stamps[start+1], Long.MAX_VALUE);
    Result result = ht.get(get);
    assertNResult(result, row, family, qualifier, stamps, values, start+1, end);
  }

  private void getVersionRangeAndVerify(HTable ht, byte [] row, byte [] family,
      byte [] qualifier, long [] stamps, byte [][] values, int start, int end)
  throws IOException {
    Get get = new Get(row);
    get.addColumn(family, qualifier);
    get.setMaxVersions(Integer.MAX_VALUE);
    get.setTimeRange(stamps[start], stamps[end]+1);
    Result result = ht.get(get);
    assertNResult(result, row, family, qualifier, stamps, values, start, end);
  }

  private void getAllVersionsAndVerify(HTable ht, byte [] row, byte [] family,
      byte [] qualifier, long [] stamps, byte [][] values, int start, int end)
  throws IOException {
    Get get = new Get(row);
    get.addColumn(family, qualifier);
    get.setMaxVersions(Integer.MAX_VALUE);
    Result result = ht.get(get);
    assertNResult(result, row, family, qualifier, stamps, values, start, end);
  }

  private void scanVersionRangeAndVerifyGreaterThan(HTable ht, byte [] row,
      byte [] family, byte [] qualifier, long [] stamps, byte [][] values,
      int start, int end)
  throws IOException {
    Scan scan = new Scan(row);
    scan.addColumn(family, qualifier);
    scan.setMaxVersions(Integer.MAX_VALUE);
    scan.setTimeRange(stamps[start+1], Long.MAX_VALUE);
    Result result = getSingleScanResult(ht, scan);
    assertNResult(result, row, family, qualifier, stamps, values, start+1, end);
  }

  private void scanVersionRangeAndVerify(HTable ht, byte [] row, byte [] family,
      byte [] qualifier, long [] stamps, byte [][] values, int start, int end)
  throws IOException {
    Scan scan = new Scan(row);
    scan.addColumn(family, qualifier);
    scan.setMaxVersions(Integer.MAX_VALUE);
    scan.setTimeRange(stamps[start], stamps[end]+1);
    Result result = getSingleScanResult(ht, scan);
    assertNResult(result, row, family, qualifier, stamps, values, start, end);
  }

  private void scanAllVersionsAndVerify(HTable ht, byte [] row, byte [] family,
      byte [] qualifier, long [] stamps, byte [][] values, int start, int end)
  throws IOException {
    Scan scan = new Scan(row);
    scan.addColumn(family, qualifier);
    scan.setMaxVersions(Integer.MAX_VALUE);
    Result result = getSingleScanResult(ht, scan);
    assertNResult(result, row, family, qualifier, stamps, values, start, end);
  }

  private void getVersionAndVerify(HTable ht, byte [] row, byte [] family,
      byte [] qualifier, long stamp, byte [] value)
  throws Exception {
    Get get = new Get(row);
    get.addColumn(family, qualifier);
    get.setTimeStamp(stamp);
    get.setMaxVersions(Integer.MAX_VALUE);
    Result result = ht.get(get);
    assertSingleResult(result, row, family, qualifier, stamp, value);
  }

  private void getVersionAndVerifyMissing(HTable ht, byte [] row, byte [] family,
      byte [] qualifier, long stamp)
  throws Exception {
    Get get = new Get(row);
    get.addColumn(family, qualifier);
    get.setTimeStamp(stamp);
    get.setMaxVersions(Integer.MAX_VALUE);
    Result result = ht.get(get);
    assertEmptyResult(result);
  }

  private void scanVersionAndVerify(HTable ht, byte [] row, byte [] family,
      byte [] qualifier, long stamp, byte [] value)
  throws Exception {
    Scan scan = new Scan(row);
    scan.addColumn(family, qualifier);
    scan.setTimeStamp(stamp);
    scan.setMaxVersions(Integer.MAX_VALUE);
    Result result = getSingleScanResult(ht, scan);
    assertSingleResult(result, row, family, qualifier, stamp, value);
  }

  private void scanVersionAndVerifyMissing(HTable ht, byte [] row,
      byte [] family, byte [] qualifier, long stamp)
  throws Exception {
    Scan scan = new Scan(row);
    scan.addColumn(family, qualifier);
    scan.setTimeStamp(stamp);
    scan.setMaxVersions(Integer.MAX_VALUE);
    Result result = getSingleScanResult(ht, scan);
    assertNullResult(result);
  }

  private void getTestNull(HTable ht, byte [] row, byte [] family,
      byte [] value)
  throws Exception {

    Get get = new Get(row);
    get.addColumn(family, null);
    Result result = ht.get(get);
    assertSingleResult(result, row, family, null, value);

    get = new Get(row);
    get.addColumn(family, HConstants.EMPTY_BYTE_ARRAY);
    result = ht.get(get);
    assertSingleResult(result, row, family, HConstants.EMPTY_BYTE_ARRAY, value);

    get = new Get(row);
    get.addFamily(family);
    result = ht.get(get);
    assertSingleResult(result, row, family, HConstants.EMPTY_BYTE_ARRAY, value);

    get = new Get(row);
    result = ht.get(get);
    assertSingleResult(result, row, family, HConstants.EMPTY_BYTE_ARRAY, value);

  }

  private void scanTestNull(HTable ht, byte [] row, byte [] family,
      byte [] value)
  throws Exception {

    Scan scan = new Scan();
    scan.addColumn(family, null);
    Result result = getSingleScanResult(ht, scan);
    assertSingleResult(result, row, family, HConstants.EMPTY_BYTE_ARRAY, value);

    scan = new Scan();
    scan.addColumn(family, HConstants.EMPTY_BYTE_ARRAY);
    result = getSingleScanResult(ht, scan);
    assertSingleResult(result, row, family, HConstants.EMPTY_BYTE_ARRAY, value);

    scan = new Scan();
    scan.addFamily(family);
    result = getSingleScanResult(ht, scan);
    assertSingleResult(result, row, family, HConstants.EMPTY_BYTE_ARRAY, value);

    scan = new Scan();
    result = getSingleScanResult(ht, scan);
    assertSingleResult(result, row, family, HConstants.EMPTY_BYTE_ARRAY, value);

  }

  private void singleRowGetTest(HTable ht, byte [][] ROWS, byte [][] FAMILIES,
      byte [][] QUALIFIERS, byte [][] VALUES)
  throws Exception {

    // Single column from memstore
    Get get = new Get(ROWS[0]);
    get.addColumn(FAMILIES[4], QUALIFIERS[0]);
    Result result = ht.get(get);
    assertSingleResult(result, ROWS[0], FAMILIES[4], QUALIFIERS[0], VALUES[0]);

    // Single column from storefile
    get = new Get(ROWS[0]);
    get.addColumn(FAMILIES[2], QUALIFIERS[2]);
    result = ht.get(get);
    assertSingleResult(result, ROWS[0], FAMILIES[2], QUALIFIERS[2], VALUES[2]);

    // Single column from storefile, family match
    get = new Get(ROWS[0]);
    get.addFamily(FAMILIES[7]);
    result = ht.get(get);
    assertSingleResult(result, ROWS[0], FAMILIES[7], QUALIFIERS[7], VALUES[7]);

    // Two columns, one from memstore one from storefile, same family,
    // wildcard match
    get = new Get(ROWS[0]);
    get.addFamily(FAMILIES[4]);
    result = ht.get(get);
    assertDoubleResult(result, ROWS[0], FAMILIES[4], QUALIFIERS[0], VALUES[0],
        FAMILIES[4], QUALIFIERS[4], VALUES[4]);

    // Two columns, one from memstore one from storefile, same family,
    // explicit match
    get = new Get(ROWS[0]);
    get.addColumn(FAMILIES[4], QUALIFIERS[0]);
    get.addColumn(FAMILIES[4], QUALIFIERS[4]);
    result = ht.get(get);
    assertDoubleResult(result, ROWS[0], FAMILIES[4], QUALIFIERS[0], VALUES[0],
        FAMILIES[4], QUALIFIERS[4], VALUES[4]);

    // Three column, one from memstore two from storefile, different families,
    // wildcard match
    get = new Get(ROWS[0]);
    get.addFamily(FAMILIES[4]);
    get.addFamily(FAMILIES[7]);
    result = ht.get(get);
    assertNResult(result, ROWS[0], FAMILIES, QUALIFIERS, VALUES,
        new int [][] { {4, 0, 0}, {4, 4, 4}, {7, 7, 7} });

    // Multiple columns from everywhere storefile, many family, wildcard
    get = new Get(ROWS[0]);
    get.addFamily(FAMILIES[2]);
    get.addFamily(FAMILIES[4]);
    get.addFamily(FAMILIES[6]);
    get.addFamily(FAMILIES[7]);
    result = ht.get(get);
    assertNResult(result, ROWS[0], FAMILIES, QUALIFIERS, VALUES,
        new int [][] {
          {2, 2, 2}, {2, 4, 4}, {4, 0, 0}, {4, 4, 4}, {6, 6, 6}, {6, 7, 7}, {7, 7, 7}
    });

    // Multiple columns from everywhere storefile, many family, wildcard
    get = new Get(ROWS[0]);
    get.addColumn(FAMILIES[2], QUALIFIERS[2]);
    get.addColumn(FAMILIES[2], QUALIFIERS[4]);
    get.addColumn(FAMILIES[4], QUALIFIERS[0]);
    get.addColumn(FAMILIES[4], QUALIFIERS[4]);
    get.addColumn(FAMILIES[6], QUALIFIERS[6]);
    get.addColumn(FAMILIES[6], QUALIFIERS[7]);
    get.addColumn(FAMILIES[7], QUALIFIERS[7]);
    get.addColumn(FAMILIES[7], QUALIFIERS[8]);
    result = ht.get(get);
    assertNResult(result, ROWS[0], FAMILIES, QUALIFIERS, VALUES,
        new int [][] {
          {2, 2, 2}, {2, 4, 4}, {4, 0, 0}, {4, 4, 4}, {6, 6, 6}, {6, 7, 7}, {7, 7, 7}
    });

    // Everything
    get = new Get(ROWS[0]);
    result = ht.get(get);
    assertNResult(result, ROWS[0], FAMILIES, QUALIFIERS, VALUES,
        new int [][] {
          {2, 2, 2}, {2, 4, 4}, {4, 0, 0}, {4, 4, 4}, {6, 6, 6}, {6, 7, 7}, {7, 7, 7}, {9, 0, 0}
    });

    // Get around inserted columns

    get = new Get(ROWS[1]);
    result = ht.get(get);
    assertEmptyResult(result);

    get = new Get(ROWS[0]);
    get.addColumn(FAMILIES[4], QUALIFIERS[3]);
    get.addColumn(FAMILIES[2], QUALIFIERS[3]);
    result = ht.get(get);
    assertEmptyResult(result);

  }

  private void singleRowScanTest(HTable ht, byte [][] ROWS, byte [][] FAMILIES,
      byte [][] QUALIFIERS, byte [][] VALUES)
  throws Exception {

    // Single column from memstore
    Scan scan = new Scan();
    scan.addColumn(FAMILIES[4], QUALIFIERS[0]);
    Result result = getSingleScanResult(ht, scan);
    assertSingleResult(result, ROWS[0], FAMILIES[4], QUALIFIERS[0], VALUES[0]);

    // Single column from storefile
    scan = new Scan();
    scan.addColumn(FAMILIES[2], QUALIFIERS[2]);
    result = getSingleScanResult(ht, scan);
    assertSingleResult(result, ROWS[0], FAMILIES[2], QUALIFIERS[2], VALUES[2]);

    // Single column from storefile, family match
    scan = new Scan();
    scan.addFamily(FAMILIES[7]);
    result = getSingleScanResult(ht, scan);
    assertSingleResult(result, ROWS[0], FAMILIES[7], QUALIFIERS[7], VALUES[7]);

    // Two columns, one from memstore one from storefile, same family,
    // wildcard match
    scan = new Scan();
    scan.addFamily(FAMILIES[4]);
    result = getSingleScanResult(ht, scan);
    assertDoubleResult(result, ROWS[0], FAMILIES[4], QUALIFIERS[0], VALUES[0],
        FAMILIES[4], QUALIFIERS[4], VALUES[4]);

    // Two columns, one from memstore one from storefile, same family,
    // explicit match
    scan = new Scan();
    scan.addColumn(FAMILIES[4], QUALIFIERS[0]);
    scan.addColumn(FAMILIES[4], QUALIFIERS[4]);
    result = getSingleScanResult(ht, scan);
    assertDoubleResult(result, ROWS[0], FAMILIES[4], QUALIFIERS[0], VALUES[0],
        FAMILIES[4], QUALIFIERS[4], VALUES[4]);

    // Three column, one from memstore two from storefile, different families,
    // wildcard match
    scan = new Scan();
    scan.addFamily(FAMILIES[4]);
    scan.addFamily(FAMILIES[7]);
    result = getSingleScanResult(ht, scan);
    assertNResult(result, ROWS[0], FAMILIES, QUALIFIERS, VALUES,
        new int [][] { {4, 0, 0}, {4, 4, 4}, {7, 7, 7} });

    // Multiple columns from everywhere storefile, many family, wildcard
    scan = new Scan();
    scan.addFamily(FAMILIES[2]);
    scan.addFamily(FAMILIES[4]);
    scan.addFamily(FAMILIES[6]);
    scan.addFamily(FAMILIES[7]);
    result = getSingleScanResult(ht, scan);
    assertNResult(result, ROWS[0], FAMILIES, QUALIFIERS, VALUES,
        new int [][] {
          {2, 2, 2}, {2, 4, 4}, {4, 0, 0}, {4, 4, 4}, {6, 6, 6}, {6, 7, 7}, {7, 7, 7}
    });

    // Multiple columns from everywhere storefile, many family, wildcard
    scan = new Scan();
    scan.addColumn(FAMILIES[2], QUALIFIERS[2]);
    scan.addColumn(FAMILIES[2], QUALIFIERS[4]);
    scan.addColumn(FAMILIES[4], QUALIFIERS[0]);
    scan.addColumn(FAMILIES[4], QUALIFIERS[4]);
    scan.addColumn(FAMILIES[6], QUALIFIERS[6]);
    scan.addColumn(FAMILIES[6], QUALIFIERS[7]);
    scan.addColumn(FAMILIES[7], QUALIFIERS[7]);
    scan.addColumn(FAMILIES[7], QUALIFIERS[8]);
    result = getSingleScanResult(ht, scan);
    assertNResult(result, ROWS[0], FAMILIES, QUALIFIERS, VALUES,
        new int [][] {
          {2, 2, 2}, {2, 4, 4}, {4, 0, 0}, {4, 4, 4}, {6, 6, 6}, {6, 7, 7}, {7, 7, 7}
    });

    // Everything
    scan = new Scan();
    result = getSingleScanResult(ht, scan);
    assertNResult(result, ROWS[0], FAMILIES, QUALIFIERS, VALUES,
        new int [][] {
          {2, 2, 2}, {2, 4, 4}, {4, 0, 0}, {4, 4, 4}, {6, 6, 6}, {6, 7, 7}, {7, 7, 7}, {9, 0, 0}
    });

    // Scan around inserted columns

    scan = new Scan(ROWS[1]);
    result = getSingleScanResult(ht, scan);
    assertNullResult(result);

    scan = new Scan();
    scan.addColumn(FAMILIES[4], QUALIFIERS[3]);
    scan.addColumn(FAMILIES[2], QUALIFIERS[3]);
    result = getSingleScanResult(ht, scan);
    assertNullResult(result);
  }

  /**
   * Verify a single column using gets.
   * Expects family and qualifier arrays to be valid for at least
   * the range:  idx-2 < idx < idx+2
   */
  private void getVerifySingleColumn(HTable ht,
      byte [][] ROWS, int ROWIDX,
      byte [][] FAMILIES, int FAMILYIDX,
      byte [][] QUALIFIERS, int QUALIFIERIDX,
      byte [][] VALUES, int VALUEIDX)
  throws Exception {

    Get get = new Get(ROWS[ROWIDX]);
    Result result = ht.get(get);
    assertSingleResult(result, ROWS[ROWIDX], FAMILIES[FAMILYIDX],
        QUALIFIERS[QUALIFIERIDX], VALUES[VALUEIDX]);

    get = new Get(ROWS[ROWIDX]);
    get.addFamily(FAMILIES[FAMILYIDX]);
    result = ht.get(get);
    assertSingleResult(result, ROWS[ROWIDX], FAMILIES[FAMILYIDX],
        QUALIFIERS[QUALIFIERIDX], VALUES[VALUEIDX]);

    get = new Get(ROWS[ROWIDX]);
    get.addFamily(FAMILIES[FAMILYIDX-2]);
    get.addFamily(FAMILIES[FAMILYIDX]);
    get.addFamily(FAMILIES[FAMILYIDX+2]);
    result = ht.get(get);
    assertSingleResult(result, ROWS[ROWIDX], FAMILIES[FAMILYIDX],
        QUALIFIERS[QUALIFIERIDX], VALUES[VALUEIDX]);

    get = new Get(ROWS[ROWIDX]);
    get.addColumn(FAMILIES[FAMILYIDX], QUALIFIERS[0]);
    result = ht.get(get);
    assertSingleResult(result, ROWS[ROWIDX], FAMILIES[FAMILYIDX],
        QUALIFIERS[QUALIFIERIDX], VALUES[VALUEIDX]);

    get = new Get(ROWS[ROWIDX]);
    get.addColumn(FAMILIES[FAMILYIDX], QUALIFIERS[1]);
    get.addFamily(FAMILIES[FAMILYIDX]);
    result = ht.get(get);
    assertSingleResult(result, ROWS[ROWIDX], FAMILIES[FAMILYIDX],
        QUALIFIERS[QUALIFIERIDX], VALUES[VALUEIDX]);

    get = new Get(ROWS[ROWIDX]);
    get.addFamily(FAMILIES[FAMILYIDX]);
    get.addColumn(FAMILIES[FAMILYIDX+1], QUALIFIERS[1]);
    get.addColumn(FAMILIES[FAMILYIDX-2], QUALIFIERS[1]);
    get.addFamily(FAMILIES[FAMILYIDX-1]);
    get.addFamily(FAMILIES[FAMILYIDX+2]);
    result = ht.get(get);
    assertSingleResult(result, ROWS[ROWIDX], FAMILIES[FAMILYIDX],
        QUALIFIERS[QUALIFIERIDX], VALUES[VALUEIDX]);

  }


  /**
   * Verify a single column using scanners.
   * Expects family and qualifier arrays to be valid for at least
   * the range:  idx-2 to idx+2
   * Expects row array to be valid for at least idx to idx+2
   */
  private void scanVerifySingleColumn(HTable ht,
      byte [][] ROWS, int ROWIDX,
      byte [][] FAMILIES, int FAMILYIDX,
      byte [][] QUALIFIERS, int QUALIFIERIDX,
      byte [][] VALUES, int VALUEIDX)
  throws Exception {

    Scan scan = new Scan();
    Result result = getSingleScanResult(ht, scan);
    assertSingleResult(result, ROWS[ROWIDX], FAMILIES[FAMILYIDX],
        QUALIFIERS[QUALIFIERIDX], VALUES[VALUEIDX]);

    scan = new Scan(ROWS[ROWIDX]);
    result = getSingleScanResult(ht, scan);
    assertSingleResult(result, ROWS[ROWIDX], FAMILIES[FAMILYIDX],
        QUALIFIERS[QUALIFIERIDX], VALUES[VALUEIDX]);

    scan = new Scan(ROWS[ROWIDX], ROWS[ROWIDX+1]);
    result = getSingleScanResult(ht, scan);
    assertSingleResult(result, ROWS[ROWIDX], FAMILIES[FAMILYIDX],
        QUALIFIERS[QUALIFIERIDX], VALUES[VALUEIDX]);

    scan = new Scan(HConstants.EMPTY_START_ROW, ROWS[ROWIDX+1]);
    result = getSingleScanResult(ht, scan);
    assertSingleResult(result, ROWS[ROWIDX], FAMILIES[FAMILYIDX],
        QUALIFIERS[QUALIFIERIDX], VALUES[VALUEIDX]);

    scan = new Scan();
    scan.addFamily(FAMILIES[FAMILYIDX]);
    result = getSingleScanResult(ht, scan);
    assertSingleResult(result, ROWS[ROWIDX], FAMILIES[FAMILYIDX],
        QUALIFIERS[QUALIFIERIDX], VALUES[VALUEIDX]);

    scan = new Scan();
    scan.addColumn(FAMILIES[FAMILYIDX], QUALIFIERS[QUALIFIERIDX]);
    result = getSingleScanResult(ht, scan);
    assertSingleResult(result, ROWS[ROWIDX], FAMILIES[FAMILYIDX],
        QUALIFIERS[QUALIFIERIDX], VALUES[VALUEIDX]);

    scan = new Scan();
    scan.addColumn(FAMILIES[FAMILYIDX], QUALIFIERS[QUALIFIERIDX+1]);
    scan.addFamily(FAMILIES[FAMILYIDX]);
    result = getSingleScanResult(ht, scan);
    assertSingleResult(result, ROWS[ROWIDX], FAMILIES[FAMILYIDX],
        QUALIFIERS[QUALIFIERIDX], VALUES[VALUEIDX]);

    scan = new Scan();
    scan.addColumn(FAMILIES[FAMILYIDX-1], QUALIFIERS[QUALIFIERIDX+1]);
    scan.addColumn(FAMILIES[FAMILYIDX], QUALIFIERS[QUALIFIERIDX]);
    scan.addFamily(FAMILIES[FAMILYIDX+1]);
    result = getSingleScanResult(ht, scan);
    assertSingleResult(result, ROWS[ROWIDX], FAMILIES[FAMILYIDX],
        QUALIFIERS[QUALIFIERIDX], VALUES[VALUEIDX]);

  }

  /**
   * Verify we do not read any values by accident around a single column
   * Same requirements as getVerifySingleColumn
   */
  private void getVerifySingleEmpty(HTable ht,
      byte [][] ROWS, int ROWIDX,
      byte [][] FAMILIES, int FAMILYIDX,
      byte [][] QUALIFIERS, int QUALIFIERIDX)
  throws Exception {

    Get get = new Get(ROWS[ROWIDX]);
    get.addFamily(FAMILIES[4]);
    get.addColumn(FAMILIES[4], QUALIFIERS[1]);
    Result result = ht.get(get);
    assertEmptyResult(result);

    get = new Get(ROWS[ROWIDX]);
    get.addFamily(FAMILIES[4]);
    get.addColumn(FAMILIES[4], QUALIFIERS[2]);
    result = ht.get(get);
    assertEmptyResult(result);

    get = new Get(ROWS[ROWIDX]);
    get.addFamily(FAMILIES[3]);
    get.addColumn(FAMILIES[4], QUALIFIERS[2]);
    get.addFamily(FAMILIES[5]);
    result = ht.get(get);
    assertEmptyResult(result);

    get = new Get(ROWS[ROWIDX+1]);
    result = ht.get(get);
    assertEmptyResult(result);

  }

  private void scanVerifySingleEmpty(HTable ht,
      byte [][] ROWS, int ROWIDX,
      byte [][] FAMILIES, int FAMILYIDX,
      byte [][] QUALIFIERS, int QUALIFIERIDX)
  throws Exception {

    Scan scan = new Scan(ROWS[ROWIDX+1]);
    Result result = getSingleScanResult(ht, scan);
    assertNullResult(result);

    scan = new Scan(ROWS[ROWIDX+1],ROWS[ROWIDX+2]);
    result = getSingleScanResult(ht, scan);
    assertNullResult(result);

    scan = new Scan(HConstants.EMPTY_START_ROW, ROWS[ROWIDX]);
    result = getSingleScanResult(ht, scan);
    assertNullResult(result);

    scan = new Scan();
    scan.addColumn(FAMILIES[FAMILYIDX], QUALIFIERS[QUALIFIERIDX+1]);
    scan.addFamily(FAMILIES[FAMILYIDX-1]);
    result = getSingleScanResult(ht, scan);
    assertNullResult(result);

  }

  //
  // Verifiers
  //

  private void assertKey(KeyValue key, byte [] row, byte [] family,
      byte [] qualifier, byte [] value)
  throws Exception {
    assertTrue("Expected row [" + Bytes.toString(row) + "] " +
        "Got row [" + Bytes.toString(key.getRow()) +"]",
        equals(row, key.getRow()));
    assertTrue("Expected family [" + Bytes.toString(family) + "] " +
        "Got family [" + Bytes.toString(key.getFamily()) + "]",
        equals(family, key.getFamily()));
    assertTrue("Expected qualifier [" + Bytes.toString(qualifier) + "] " +
        "Got qualifier [" + Bytes.toString(key.getQualifier()) + "]",
        equals(qualifier, key.getQualifier()));
    assertTrue("Expected value [" + Bytes.toString(value) + "] " +
        "Got value [" + Bytes.toString(key.getValue()) + "]",
        equals(value, key.getValue()));
  }

  private void assertNumKeys(Result result, int n) throws Exception {
    assertTrue("Expected " + n + " keys but got " + result.size(),
        result.size() == n);
  }

  private void assertNResult(Result result, byte [] row,
      byte [][] families, byte [][] qualifiers, byte [][] values,
      int [][] idxs)
  throws Exception {
    assertTrue("Expected row [" + Bytes.toString(row) + "] " +
        "Got row [" + Bytes.toString(result.getRow()) +"]",
        equals(row, result.getRow()));
    assertTrue("Expected " + idxs.length + " keys but result contains "
        + result.size(), result.size() == idxs.length);

    KeyValue [] keys = result.sorted();

    for(int i=0;i<keys.length;i++) {
      byte [] family = families[idxs[i][0]];
      byte [] qualifier = qualifiers[idxs[i][1]];
      byte [] value = values[idxs[i][2]];
      KeyValue key = keys[i];

      assertTrue("(" + i + ") Expected family [" + Bytes.toString(family)
          + "] " + "Got family [" + Bytes.toString(key.getFamily()) + "]",
          equals(family, key.getFamily()));
      assertTrue("(" + i + ") Expected qualifier [" + Bytes.toString(qualifier)
          + "] " + "Got qualifier [" + Bytes.toString(key.getQualifier()) + "]",
          equals(qualifier, key.getQualifier()));
      assertTrue("(" + i + ") Expected value [" + Bytes.toString(value) + "] "
          + "Got value [" + Bytes.toString(key.getValue()) + "]",
          equals(value, key.getValue()));
    }
  }

  private void assertNResult(Result result, byte [] row,
      byte [] family, byte [] qualifier, long [] stamps, byte [][] values,
      int start, int end)
  throws IOException {
    assertTrue("Expected row [" + Bytes.toString(row) + "] " +
        "Got row [" + Bytes.toString(result.getRow()) +"]",
        equals(row, result.getRow()));
    int expectedResults = end - start + 1;
    assertTrue("Expected " + expectedResults + " keys but result contains "
        + result.size(), result.size() == expectedResults);

    KeyValue [] keys = result.sorted();

    for(int i=0;i<keys.length;i++) {
      byte [] value = values[end-i];
      long ts = stamps[end-i];
      KeyValue key = keys[i];

      assertTrue("(" + i + ") Expected family [" + Bytes.toString(family)
          + "] " + "Got family [" + Bytes.toString(key.getFamily()) + "]",
          equals(family, key.getFamily()));
      assertTrue("(" + i + ") Expected qualifier [" + Bytes.toString(qualifier)
          + "] " + "Got qualifier [" + Bytes.toString(key.getQualifier()) + "]",
          equals(qualifier, key.getQualifier()));
      assertTrue("Expected ts [" + ts + "] " +
          "Got ts [" + key.getTimestamp() + "]", ts == key.getTimestamp());
      assertTrue("(" + i + ") Expected value [" + Bytes.toString(value) + "] "
          + "Got value [" + Bytes.toString(key.getValue()) + "]",
          equals(value, key.getValue()));
    }
  }

  /**
   * Validate that result contains two specified keys, exactly.
   * It is assumed key A sorts before key B.
   */
  private void assertDoubleResult(Result result, byte [] row,
      byte [] familyA, byte [] qualifierA, byte [] valueA,
      byte [] familyB, byte [] qualifierB, byte [] valueB)
  throws Exception {
    assertTrue("Expected row [" + Bytes.toString(row) + "] " +
        "Got row [" + Bytes.toString(result.getRow()) +"]",
        equals(row, result.getRow()));
    assertTrue("Expected two keys but result contains " + result.size(),
        result.size() == 2);
    KeyValue [] kv = result.sorted();
    KeyValue kvA = kv[0];
    assertTrue("(A) Expected family [" + Bytes.toString(familyA) + "] " +
        "Got family [" + Bytes.toString(kvA.getFamily()) + "]",
        equals(familyA, kvA.getFamily()));
    assertTrue("(A) Expected qualifier [" + Bytes.toString(qualifierA) + "] " +
        "Got qualifier [" + Bytes.toString(kvA.getQualifier()) + "]",
        equals(qualifierA, kvA.getQualifier()));
    assertTrue("(A) Expected value [" + Bytes.toString(valueA) + "] " +
        "Got value [" + Bytes.toString(kvA.getValue()) + "]",
        equals(valueA, kvA.getValue()));
    KeyValue kvB = kv[1];
    assertTrue("(B) Expected family [" + Bytes.toString(familyB) + "] " +
        "Got family [" + Bytes.toString(kvB.getFamily()) + "]",
        equals(familyB, kvB.getFamily()));
    assertTrue("(B) Expected qualifier [" + Bytes.toString(qualifierB) + "] " +
        "Got qualifier [" + Bytes.toString(kvB.getQualifier()) + "]",
        equals(qualifierB, kvB.getQualifier()));
    assertTrue("(B) Expected value [" + Bytes.toString(valueB) + "] " +
        "Got value [" + Bytes.toString(kvB.getValue()) + "]",
        equals(valueB, kvB.getValue()));
  }

  private void assertSingleResult(Result result, byte [] row, byte [] family,
      byte [] qualifier, byte [] value)
  throws Exception {
    assertTrue("Expected row [" + Bytes.toString(row) + "] " +
        "Got row [" + Bytes.toString(result.getRow()) +"]",
        equals(row, result.getRow()));
    assertTrue("Expected a single key but result contains " + result.size(),
        result.size() == 1);
    KeyValue kv = result.sorted()[0];
    assertTrue("Expected family [" + Bytes.toString(family) + "] " +
        "Got family [" + Bytes.toString(kv.getFamily()) + "]",
        equals(family, kv.getFamily()));
    assertTrue("Expected qualifier [" + Bytes.toString(qualifier) + "] " +
        "Got qualifier [" + Bytes.toString(kv.getQualifier()) + "]",
        equals(qualifier, kv.getQualifier()));
    assertTrue("Expected value [" + Bytes.toString(value) + "] " +
        "Got value [" + Bytes.toString(kv.getValue()) + "]",
        equals(value, kv.getValue()));
  }

  private void assertSingleResult(Result result, byte [] row, byte [] family,
      byte [] qualifier, long ts, byte [] value)
  throws Exception {
    assertTrue("Expected row [" + Bytes.toString(row) + "] " +
        "Got row [" + Bytes.toString(result.getRow()) +"]",
        equals(row, result.getRow()));
    assertTrue("Expected a single key but result contains " + result.size(),
        result.size() == 1);
    KeyValue kv = result.sorted()[0];
    assertTrue("Expected family [" + Bytes.toString(family) + "] " +
        "Got family [" + Bytes.toString(kv.getFamily()) + "]",
        equals(family, kv.getFamily()));
    assertTrue("Expected qualifier [" + Bytes.toString(qualifier) + "] " +
        "Got qualifier [" + Bytes.toString(kv.getQualifier()) + "]",
        equals(qualifier, kv.getQualifier()));
    assertTrue("Expected ts [" + ts + "] " +
        "Got ts [" + kv.getTimestamp() + "]", ts == kv.getTimestamp());
    assertTrue("Expected value [" + Bytes.toString(value) + "] " +
        "Got value [" + Bytes.toString(kv.getValue()) + "]",
        equals(value, kv.getValue()));
  }

  private void assertEmptyResult(Result result) throws Exception {
    assertTrue("expected an empty result but result contains " +
        result.size() + " keys", result.isEmpty());
  }

  private void assertNullResult(Result result) throws Exception {
    assertTrue("expected null result but received a non-null result",
        result == null);
  }

  //
  // Helpers
  //

  private Result getSingleScanResult(HTable ht, Scan scan) throws IOException {
    ResultScanner scanner = ht.getScanner(scan);
    Result result = scanner.next();
    scanner.close();
    return result;
  }

  private byte [][] makeNAscii(byte [] base, int n) {
    if(n > 256) {
      return makeNBig(base, n);
    }
    byte [][] ret = new byte[n][];
    for(int i=0;i<n;i++) {
      byte [] tail = Bytes.toBytes(Integer.toString(i));
      ret[i] = Bytes.add(base, tail);
    }
    return ret;
  }

  private byte [][] makeN(byte [] base, int n) {
    if (n > 256) {
      return makeNBig(base, n);
    }
    byte [][] ret = new byte[n][];
    for(int i=0;i<n;i++) {
      ret[i] = Bytes.add(base, new byte[]{(byte)i});
    }
    return ret;
  }

  private byte [][] makeNBig(byte [] base, int n) {
    byte [][] ret = new byte[n][];
    for(int i=0;i<n;i++) {
      int byteA = (i % 256);
      int byteB = (i >> 8);
      ret[i] = Bytes.add(base, new byte[]{(byte)byteB,(byte)byteA});
    }
    return ret;
  }

  private long [] makeStamps(int n) {
    long [] stamps = new long[n];
    for(int i=0;i<n;i++) stamps[i] = i+1;
    return stamps;
  }

  private boolean equals(byte [] left, byte [] right) {
    if(left == null && right == null) return true;
    if(left == null && right.length == 0) return true;
    if(right == null && left.length == 0) return true;
    return Bytes.equals(left, right);
  }
}