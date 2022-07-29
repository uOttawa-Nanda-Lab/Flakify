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
package org.apache.hadoop.hbase;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.KeyValue.KVComparator;
import org.apache.hadoop.hbase.KeyValue.Type;
import org.apache.hadoop.hbase.util.Bytes;

public class TestKeyValue extends TestCase {
  private final Log LOG = LogFactory.getLog(this.getClass().getName());

  private void check(final byte [] row, final byte [] family, byte [] qualifier,
    final long timestamp, final byte [] value) {
    KeyValue kv = new KeyValue(row, family, qualifier, timestamp, value);
    assertTrue(Bytes.compareTo(kv.getRow(), row) == 0);
    assertTrue(kv.matchingColumn(family, qualifier));
    // Call toString to make sure it works.
    LOG.info(kv.toString());
  }

  public void testMoreComparisons() throws Exception {
	long now = System.currentTimeMillis();
	KeyValue a = new KeyValue(Bytes.toBytes(".META.,,99999999999999"), now);
	KeyValue b = new KeyValue(Bytes.toBytes(".META.,,1"), now);
	KVComparator c = new KeyValue.RootComparator();
	assertTrue(c.compare(b, a) < 0);
	KeyValue aa = new KeyValue(Bytes.toBytes(".META.,,1"), now);
	KeyValue bb = new KeyValue(Bytes.toBytes(".META.,,1"), Bytes.toBytes("info"), Bytes.toBytes("regioninfo"),
			1235943454602L, (byte[]) null);
	assertTrue(c.compare(aa, bb) < 0);
	KeyValue aaa = new KeyValue(Bytes.toBytes("TestScanMultipleVersions,row_0500,1236020145502"), now);
	KeyValue bbb = new KeyValue(Bytes.toBytes("TestScanMultipleVersions,,99999999999999"), now);
	c = new KeyValue.MetaComparator();
	assertTrue(c.compare(bbb, aaa) < 0);
	KeyValue aaaa = new KeyValue(Bytes.toBytes("TestScanMultipleVersions,,1236023996656"), Bytes.toBytes("info"),
			Bytes.toBytes("regioninfo"), 1236024396271L, (byte[]) null);
	assertTrue(c.compare(aaaa, bbb) < 0);
	KeyValue x = new KeyValue(Bytes.toBytes("TestScanMultipleVersions,row_0500,1236034574162"), Bytes.toBytes("info"),
			Bytes.toBytes(""), 9223372036854775807L, (byte[]) null);
	KeyValue y = new KeyValue(Bytes.toBytes("TestScanMultipleVersions,row_0500,1236034574162"), Bytes.toBytes("info"),
			Bytes.toBytes("regioninfo"), 1236034574912L, (byte[]) null);
	assertTrue(c.compare(x, y) < 0);
	comparisons(new KeyValue.MetaComparator());
	comparisons(new KeyValue.KVComparator());
	metacomparisons(new KeyValue.RootComparator());
	metacomparisons(new KeyValue.MetaComparator());
}

  private void metacomparisons(final KeyValue.MetaComparator c) {
    long now = System.currentTimeMillis();
    assertTrue(c.compare(new KeyValue(Bytes.toBytes(".META.,a,,0,1"), now),
      new KeyValue(Bytes.toBytes(".META.,a,,0,1"), now)) == 0);
    KeyValue a = new KeyValue(Bytes.toBytes(".META.,a,,0,1"), now);
    KeyValue b = new KeyValue(Bytes.toBytes(".META.,a,,0,2"), now);
    assertTrue(c.compare(a, b) < 0);
    assertTrue(c.compare(new KeyValue(Bytes.toBytes(".META.,a,,0,2"), now),
      new KeyValue(Bytes.toBytes(".META.,a,,0,1"), now)) > 0);
  }

  private void comparisons(final KeyValue.KVComparator c) {
    long now = System.currentTimeMillis();
    assertTrue(c.compare(new KeyValue(Bytes.toBytes(".META.,,1"), now),
      new KeyValue(Bytes.toBytes(".META.,,1"), now)) == 0);
    assertTrue(c.compare(new KeyValue(Bytes.toBytes(".META.,,1"), now),
      new KeyValue(Bytes.toBytes(".META.,,2"), now)) < 0);
    assertTrue(c.compare(new KeyValue(Bytes.toBytes(".META.,,2"), now),
      new KeyValue(Bytes.toBytes(".META.,,1"), now)) > 0);
  }
}
