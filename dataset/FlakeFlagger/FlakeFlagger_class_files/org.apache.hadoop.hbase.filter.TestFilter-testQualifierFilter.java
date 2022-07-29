/*
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

package org.apache.hadoop.hbase.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.HBaseTestCase;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FilterList.Operator;
import org.apache.hadoop.hbase.regionserver.HRegion;
import org.apache.hadoop.hbase.regionserver.InternalScanner;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Test filters at the HRegion doorstep.
 */
public class TestFilter extends HBaseTestCase {
  private final Log LOG = LogFactory.getLog(this.getClass());
  private HRegion region;

  //
  // Rows, Qualifiers, and Values are in two groups, One and Two.
  //

  private static final byte [][] ROWS_ONE = {
      Bytes.toBytes("testRowOne-0"), Bytes.toBytes("testRowOne-1"),
      Bytes.toBytes("testRowOne-2"), Bytes.toBytes("testRowOne-3")
  };

  private static final byte [][] ROWS_TWO = {
      Bytes.toBytes("testRowTwo-0"), Bytes.toBytes("testRowTwo-1"),
      Bytes.toBytes("testRowTwo-2"), Bytes.toBytes("testRowTwo-3")
  };

  private static final byte [][] FAMILIES = {
    Bytes.toBytes("testFamilyOne"), Bytes.toBytes("testFamilyTwo")
  };

  private static final byte [][] QUALIFIERS_ONE = {
    Bytes.toBytes("testQualifierOne-0"), Bytes.toBytes("testQualifierOne-1"),
    Bytes.toBytes("testQualifierOne-2"), Bytes.toBytes("testQualifierOne-3")
  };

  private static final byte [][] QUALIFIERS_TWO = {
    Bytes.toBytes("testQualifierTwo-0"), Bytes.toBytes("testQualifierTwo-1"),
    Bytes.toBytes("testQualifierTwo-2"), Bytes.toBytes("testQualifierTwo-3")
  };

  private static final byte [][] VALUES = {
    Bytes.toBytes("testValueOne"), Bytes.toBytes("testValueTwo")
  };

  private long numRows = ROWS_ONE.length + ROWS_TWO.length;
  private long colsPerRow = FAMILIES.length * QUALIFIERS_ONE.length;


  protected void setUp() throws Exception {
    super.setUp();
    HTableDescriptor htd = new HTableDescriptor(getName());
    htd.addFamily(new HColumnDescriptor(FAMILIES[0]));
    htd.addFamily(new HColumnDescriptor(FAMILIES[1]));
    HRegionInfo info = new HRegionInfo(htd, null, null, false);
    this.region = HRegion.createHRegion(info, this.testDir, this.conf);

    // Insert first half
    for(byte [] ROW : ROWS_ONE) {
      Put p = new Put(ROW);
      for(byte [] QUALIFIER : QUALIFIERS_ONE) {
        p.add(FAMILIES[0], QUALIFIER, VALUES[0]);
      }
      this.region.put(p);
    }
    for(byte [] ROW : ROWS_TWO) {
      Put p = new Put(ROW);
      for(byte [] QUALIFIER : QUALIFIERS_TWO) {
        p.add(FAMILIES[1], QUALIFIER, VALUES[1]);
      }
      this.region.put(p);
    }

    // Flush
    this.region.flushcache();

    // Insert second half (reverse families)
    for(byte [] ROW : ROWS_ONE) {
      Put p = new Put(ROW);
      for(byte [] QUALIFIER : QUALIFIERS_ONE) {
        p.add(FAMILIES[1], QUALIFIER, VALUES[0]);
      }
      this.region.put(p);
    }
    for(byte [] ROW : ROWS_TWO) {
      Put p = new Put(ROW);
      for(byte [] QUALIFIER : QUALIFIERS_TWO) {
        p.add(FAMILIES[0], QUALIFIER, VALUES[1]);
      }
      this.region.put(p);
    }

    // Delete the second qualifier from all rows and families
    for(byte [] ROW : ROWS_ONE) {
      Delete d = new Delete(ROW);
      d.deleteColumns(FAMILIES[0], QUALIFIERS_ONE[1]);
      d.deleteColumns(FAMILIES[1], QUALIFIERS_ONE[1]);
      this.region.delete(d, null, false);
    }
    for(byte [] ROW : ROWS_TWO) {
      Delete d = new Delete(ROW);
      d.deleteColumns(FAMILIES[0], QUALIFIERS_TWO[1]);
      d.deleteColumns(FAMILIES[1], QUALIFIERS_TWO[1]);
      this.region.delete(d, null, false);
    }
    colsPerRow -= 2;

    // Delete the second rows from both groups, one column at a time
    for(byte [] QUALIFIER : QUALIFIERS_ONE) {
      Delete d = new Delete(ROWS_ONE[1]);
      d.deleteColumns(FAMILIES[0], QUALIFIER);
      d.deleteColumns(FAMILIES[1], QUALIFIER);
      this.region.delete(d, null, false);
    }
    for(byte [] QUALIFIER : QUALIFIERS_TWO) {
      Delete d = new Delete(ROWS_TWO[1]);
      d.deleteColumns(FAMILIES[0], QUALIFIER);
      d.deleteColumns(FAMILIES[1], QUALIFIER);
      this.region.delete(d, null, false);
    }
    numRows -= 2;
  }

  protected void tearDown() throws Exception {
    super.tearDown();
    this.region.close();
  }

  public void testQualifierFilter() throws IOException {

    // Match two keys (one from each family) in half the rows
    long expectedRows = this.numRows / 2;
    long expectedKeys = 2;
    Filter f = new QualifierFilter(CompareOp.EQUAL,
        new BinaryComparator(Bytes.toBytes("testQualifierOne-2")));
    Scan s = new Scan();
    s.setFilter(f);
    verifyScanNoEarlyOut(s, expectedRows, expectedKeys);

    // Match keys less than same qualifier
    // Expect only two keys (one from each family) in half the rows
    expectedRows = this.numRows / 2;
    expectedKeys = 2;
    f = new QualifierFilter(CompareOp.LESS,
        new BinaryComparator(Bytes.toBytes("testQualifierOne-2")));
    s = new Scan();
    s.setFilter(f);
    verifyScanNoEarlyOut(s, expectedRows, expectedKeys);

    // Match keys less than or equal
    // Expect four keys (two from each family) in half the rows
    expectedRows = this.numRows / 2;
    expectedKeys = 4;
    f = new QualifierFilter(CompareOp.LESS_OR_EQUAL,
        new BinaryComparator(Bytes.toBytes("testQualifierOne-2")));
    s = new Scan();
    s.setFilter(f);
    verifyScanNoEarlyOut(s, expectedRows, expectedKeys);

    // Match keys not equal
    // Expect four keys (two from each family)
    // Only look in first group of rows
    expectedRows = this.numRows / 2;
    expectedKeys = 4;
    f = new QualifierFilter(CompareOp.NOT_EQUAL,
        new BinaryComparator(Bytes.toBytes("testQualifierOne-2")));
    s = new Scan(HConstants.EMPTY_START_ROW, Bytes.toBytes("testRowTwo"));
    s.setFilter(f);
    verifyScanNoEarlyOut(s, expectedRows, expectedKeys);

    // Match keys greater or equal
    // Expect four keys (two from each family)
    // Only look in first group of rows
    expectedRows = this.numRows / 2;
    expectedKeys = 4;
    f = new QualifierFilter(CompareOp.GREATER_OR_EQUAL,
        new BinaryComparator(Bytes.toBytes("testQualifierOne-2")));
    s = new Scan(HConstants.EMPTY_START_ROW, Bytes.toBytes("testRowTwo"));
    s.setFilter(f);
    verifyScanNoEarlyOut(s, expectedRows, expectedKeys);

    // Match keys greater
    // Expect two keys (one from each family)
    // Only look in first group of rows
    expectedRows = this.numRows / 2;
    expectedKeys = 2;
    f = new QualifierFilter(CompareOp.GREATER,
        new BinaryComparator(Bytes.toBytes("testQualifierOne-2")));
    s = new Scan(HConstants.EMPTY_START_ROW, Bytes.toBytes("testRowTwo"));
    s.setFilter(f);
    verifyScanNoEarlyOut(s, expectedRows, expectedKeys);

    // Match keys not equal to
    // Look across rows and fully validate the keys and ordering
    // Expect varied numbers of keys, 4 per row in group one, 6 per row in group two
    f = new QualifierFilter(CompareOp.NOT_EQUAL,
        new BinaryComparator(QUALIFIERS_ONE[2]));
    s = new Scan();
    s.setFilter(f);

    KeyValue [] kvs = {
        // testRowOne-0
        new KeyValue(ROWS_ONE[0], FAMILIES[0], QUALIFIERS_ONE[0], VALUES[0]),
        new KeyValue(ROWS_ONE[0], FAMILIES[0], QUALIFIERS_ONE[3], VALUES[0]),
        new KeyValue(ROWS_ONE[0], FAMILIES[1], QUALIFIERS_ONE[0], VALUES[0]),
        new KeyValue(ROWS_ONE[0], FAMILIES[1], QUALIFIERS_ONE[3], VALUES[0]),
        // testRowOne-2
        new KeyValue(ROWS_ONE[2], FAMILIES[0], QUALIFIERS_ONE[0], VALUES[0]),
        new KeyValue(ROWS_ONE[2], FAMILIES[0], QUALIFIERS_ONE[3], VALUES[0]),
        new KeyValue(ROWS_ONE[2], FAMILIES[1], QUALIFIERS_ONE[0], VALUES[0]),
        new KeyValue(ROWS_ONE[2], FAMILIES[1], QUALIFIERS_ONE[3], VALUES[0]),
        // testRowOne-3
        new KeyValue(ROWS_ONE[3], FAMILIES[0], QUALIFIERS_ONE[0], VALUES[0]),
        new KeyValue(ROWS_ONE[3], FAMILIES[0], QUALIFIERS_ONE[3], VALUES[0]),
        new KeyValue(ROWS_ONE[3], FAMILIES[1], QUALIFIERS_ONE[0], VALUES[0]),
        new KeyValue(ROWS_ONE[3], FAMILIES[1], QUALIFIERS_ONE[3], VALUES[0]),
        // testRowTwo-0
        new KeyValue(ROWS_TWO[0], FAMILIES[0], QUALIFIERS_TWO[0], VALUES[1]),
        new KeyValue(ROWS_TWO[0], FAMILIES[0], QUALIFIERS_TWO[2], VALUES[1]),
        new KeyValue(ROWS_TWO[0], FAMILIES[0], QUALIFIERS_TWO[3], VALUES[1]),
        new KeyValue(ROWS_TWO[0], FAMILIES[1], QUALIFIERS_TWO[0], VALUES[1]),
        new KeyValue(ROWS_TWO[0], FAMILIES[1], QUALIFIERS_TWO[2], VALUES[1]),
        new KeyValue(ROWS_TWO[0], FAMILIES[1], QUALIFIERS_TWO[3], VALUES[1]),
        // testRowTwo-2
        new KeyValue(ROWS_TWO[2], FAMILIES[0], QUALIFIERS_TWO[0], VALUES[1]),
        new KeyValue(ROWS_TWO[2], FAMILIES[0], QUALIFIERS_TWO[2], VALUES[1]),
        new KeyValue(ROWS_TWO[2], FAMILIES[0], QUALIFIERS_TWO[3], VALUES[1]),
        new KeyValue(ROWS_TWO[2], FAMILIES[1], QUALIFIERS_TWO[0], VALUES[1]),
        new KeyValue(ROWS_TWO[2], FAMILIES[1], QUALIFIERS_TWO[2], VALUES[1]),
        new KeyValue(ROWS_TWO[2], FAMILIES[1], QUALIFIERS_TWO[3], VALUES[1]),
        // testRowTwo-3
        new KeyValue(ROWS_TWO[3], FAMILIES[0], QUALIFIERS_TWO[0], VALUES[1]),
        new KeyValue(ROWS_TWO[3], FAMILIES[0], QUALIFIERS_TWO[2], VALUES[1]),
        new KeyValue(ROWS_TWO[3], FAMILIES[0], QUALIFIERS_TWO[3], VALUES[1]),
        new KeyValue(ROWS_TWO[3], FAMILIES[1], QUALIFIERS_TWO[0], VALUES[1]),
        new KeyValue(ROWS_TWO[3], FAMILIES[1], QUALIFIERS_TWO[2], VALUES[1]),
        new KeyValue(ROWS_TWO[3], FAMILIES[1], QUALIFIERS_TWO[3], VALUES[1]),
    };
    verifyScanFull(s, kvs);


    // Test across rows and groups with a regex
    // Filter out "test*-2"
    // Expect 4 keys per row across both groups
    f = new QualifierFilter(CompareOp.NOT_EQUAL,
        new RegexStringComparator("test.+-2"));
    s = new Scan();
    s.setFilter(f);

    kvs = new KeyValue [] {
        // testRowOne-0
        new KeyValue(ROWS_ONE[0], FAMILIES[0], QUALIFIERS_ONE[0], VALUES[0]),
        new KeyValue(ROWS_ONE[0], FAMILIES[0], QUALIFIERS_ONE[3], VALUES[0]),
        new KeyValue(ROWS_ONE[0], FAMILIES[1], QUALIFIERS_ONE[0], VALUES[0]),
        new KeyValue(ROWS_ONE[0], FAMILIES[1], QUALIFIERS_ONE[3], VALUES[0]),
        // testRowOne-2
        new KeyValue(ROWS_ONE[2], FAMILIES[0], QUALIFIERS_ONE[0], VALUES[0]),
        new KeyValue(ROWS_ONE[2], FAMILIES[0], QUALIFIERS_ONE[3], VALUES[0]),
        new KeyValue(ROWS_ONE[2], FAMILIES[1], QUALIFIERS_ONE[0], VALUES[0]),
        new KeyValue(ROWS_ONE[2], FAMILIES[1], QUALIFIERS_ONE[3], VALUES[0]),
        // testRowOne-3
        new KeyValue(ROWS_ONE[3], FAMILIES[0], QUALIFIERS_ONE[0], VALUES[0]),
        new KeyValue(ROWS_ONE[3], FAMILIES[0], QUALIFIERS_ONE[3], VALUES[0]),
        new KeyValue(ROWS_ONE[3], FAMILIES[1], QUALIFIERS_ONE[0], VALUES[0]),
        new KeyValue(ROWS_ONE[3], FAMILIES[1], QUALIFIERS_ONE[3], VALUES[0]),
        // testRowTwo-0
        new KeyValue(ROWS_TWO[0], FAMILIES[0], QUALIFIERS_TWO[0], VALUES[1]),
        new KeyValue(ROWS_TWO[0], FAMILIES[0], QUALIFIERS_TWO[3], VALUES[1]),
        new KeyValue(ROWS_TWO[0], FAMILIES[1], QUALIFIERS_TWO[0], VALUES[1]),
        new KeyValue(ROWS_TWO[0], FAMILIES[1], QUALIFIERS_TWO[3], VALUES[1]),
        // testRowTwo-2
        new KeyValue(ROWS_TWO[2], FAMILIES[0], QUALIFIERS_TWO[0], VALUES[1]),
        new KeyValue(ROWS_TWO[2], FAMILIES[0], QUALIFIERS_TWO[3], VALUES[1]),
        new KeyValue(ROWS_TWO[2], FAMILIES[1], QUALIFIERS_TWO[0], VALUES[1]),
        new KeyValue(ROWS_TWO[2], FAMILIES[1], QUALIFIERS_TWO[3], VALUES[1]),
        // testRowTwo-3
        new KeyValue(ROWS_TWO[3], FAMILIES[0], QUALIFIERS_TWO[0], VALUES[1]),
        new KeyValue(ROWS_TWO[3], FAMILIES[0], QUALIFIERS_TWO[3], VALUES[1]),
        new KeyValue(ROWS_TWO[3], FAMILIES[1], QUALIFIERS_TWO[0], VALUES[1]),
        new KeyValue(ROWS_TWO[3], FAMILIES[1], QUALIFIERS_TWO[3], VALUES[1]),
    };
    verifyScanFull(s, kvs);

  }

  private void verifyScan(Scan s, long expectedRows, long expectedKeys)
  throws IOException {
    InternalScanner scanner = this.region.getScanner(s);
    List<KeyValue> results = new ArrayList<KeyValue>();
    int i = 0;
    for (boolean done = true; done; i++) {
      done = scanner.next(results);
      Arrays.sort(results.toArray(new KeyValue[results.size()]),
          KeyValue.COMPARATOR);
      LOG.info("counter=" + i + ", " + results);
      if (results.isEmpty()) break;
      assertTrue("Scanned too many rows! Only expected " + expectedRows +
          " total but already scanned " + (i+1), expectedRows > i);
      assertEquals("Expected " + expectedKeys + " keys per row but " +
          "returned " + results.size(), expectedKeys, results.size());
      results.clear();
    }
    assertEquals("Expected " + expectedRows + " rows but scanned " + i +
        " rows", expectedRows, i);
  }



  private void verifyScanNoEarlyOut(Scan s, long expectedRows,
      long expectedKeys)
  throws IOException {
    InternalScanner scanner = this.region.getScanner(s);
    List<KeyValue> results = new ArrayList<KeyValue>();
    int i = 0;
    for (boolean done = true; done; i++) {
      done = scanner.next(results);
      Arrays.sort(results.toArray(new KeyValue[results.size()]),
          KeyValue.COMPARATOR);
      LOG.info("counter=" + i + ", " + results);
      if(results.isEmpty()) break;
      assertTrue("Scanned too many rows! Only expected " + expectedRows +
          " total but already scanned " + (i+1), expectedRows > i);
      assertEquals("Expected " + expectedKeys + " keys per row but " +
          "returned " + results.size(), expectedKeys, results.size());
      results.clear();
    }
    assertEquals("Expected " + expectedRows + " rows but scanned " + i +
        " rows", expectedRows, i);
  }

  private void verifyScanFull(Scan s, KeyValue [] kvs)
  throws IOException {
    InternalScanner scanner = this.region.getScanner(s);
    List<KeyValue> results = new ArrayList<KeyValue>();
    int row = 0;
    int idx = 0;
    for (boolean done = true; done; row++) {
      done = scanner.next(results);
      Arrays.sort(results.toArray(new KeyValue[results.size()]),
          KeyValue.COMPARATOR);
      if(results.isEmpty()) break;
      assertTrue("Scanned too many keys! Only expected " + kvs.length +
          " total but already scanned " + (results.size() + idx) +
          (results.isEmpty() ? "" : "(" + results.get(0).toString() + ")"),
          kvs.length >= idx + results.size());
      for(KeyValue kv : results) {
        LOG.info("row=" + row + ", result=" + kv.toString() +
            ", match=" + kvs[idx].toString());
        assertTrue("Row mismatch",
            Bytes.equals(kv.getRow(), kvs[idx].getRow()));
        assertTrue("Family mismatch",
            Bytes.equals(kv.getFamily(), kvs[idx].getFamily()));
        assertTrue("Qualifier mismatch",
            Bytes.equals(kv.getQualifier(), kvs[idx].getQualifier()));
        assertTrue("Value mismatch",
            Bytes.equals(kv.getValue(), kvs[idx].getValue()));
        idx++;
      }
      results.clear();
    }
    LOG.info("Looked at " + row + " rows with " + idx + " keys");
    assertEquals("Expected " + kvs.length + " total keys but scanned " + idx,
        kvs.length, idx);
  }
}
