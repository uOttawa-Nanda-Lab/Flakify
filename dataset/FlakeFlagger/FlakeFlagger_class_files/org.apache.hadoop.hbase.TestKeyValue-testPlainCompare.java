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

  public void testPlainCompare() throws Exception {
	final byte[] a = Bytes.toBytes("aaa");
	final byte[] b = Bytes.toBytes("bbb");
	final byte[] fam = Bytes.toBytes("col");
	final byte[] qf = Bytes.toBytes("umn");
	KeyValue aaa = new KeyValue(a, fam, qf, a);
	KeyValue bbb = new KeyValue(b, fam, qf, b);
	byte[] keyabb = aaa.getKey();
	byte[] keybbb = bbb.getKey();
	assertTrue(KeyValue.COMPARATOR.compare(aaa, bbb) < 0);
	assertTrue(KeyValue.KEY_COMPARATOR.compare(keyabb, 0, keyabb.length, keybbb, 0, keybbb.length) < 0);
	assertTrue(KeyValue.COMPARATOR.compare(bbb, aaa) > 0);
	assertTrue(KeyValue.KEY_COMPARATOR.compare(keybbb, 0, keybbb.length, keyabb, 0, keyabb.length) > 0);
	assertTrue(KeyValue.COMPARATOR.compare(bbb, bbb) == 0);
	assertTrue(KeyValue.KEY_COMPARATOR.compare(keybbb, 0, keybbb.length, keybbb, 0, keybbb.length) == 0);
	assertTrue(KeyValue.COMPARATOR.compare(aaa, aaa) == 0);
	assertTrue(KeyValue.KEY_COMPARATOR.compare(keyabb, 0, keyabb.length, keyabb, 0, keyabb.length) == 0);
	aaa = new KeyValue(a, fam, qf, 1, a);
	bbb = new KeyValue(a, fam, qf, 2, a);
	assertTrue(KeyValue.COMPARATOR.compare(aaa, bbb) > 0);
	assertTrue(KeyValue.COMPARATOR.compare(bbb, aaa) < 0);
	assertTrue(KeyValue.COMPARATOR.compare(aaa, aaa) == 0);
	aaa = new KeyValue(a, fam, qf, 1, KeyValue.Type.Delete, a);
	bbb = new KeyValue(a, fam, qf, 1, a);
	assertTrue(KeyValue.COMPARATOR.compare(aaa, bbb) < 0);
	assertTrue(KeyValue.COMPARATOR.compare(bbb, aaa) > 0);
	assertTrue(KeyValue.COMPARATOR.compare(aaa, aaa) == 0);
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
