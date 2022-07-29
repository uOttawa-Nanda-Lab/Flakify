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

  public void testBinaryKeys() throws Exception{Set<KeyValue> set=new TreeSet<KeyValue>(KeyValue.COMPARATOR);final byte[] fam=Bytes.toBytes("col");final byte[] qf=Bytes.toBytes("umn");final byte[] nb=new byte[0];KeyValue[] keys={new KeyValue(Bytes.toBytes("aaaaa,\u0000\u0000,2"),fam,qf,2,nb),new KeyValue(Bytes.toBytes("aaaaa,\u0001,3"),fam,qf,3,nb),new KeyValue(Bytes.toBytes("aaaaa,,1"),fam,qf,1,nb),new KeyValue(Bytes.toBytes("aaaaa,\u1000,5"),fam,qf,5,nb),new KeyValue(Bytes.toBytes("aaaaa,a,4"),fam,qf,4,nb),new KeyValue(Bytes.toBytes("a,a,0"),fam,qf,0,nb)};for (int i=0;i < keys.length;i++){set.add(keys[i]);}boolean assertion=false;int count=0;try {for (KeyValue k:set){assertTrue(count++ == k.getTimestamp());}} catch (junit.framework.AssertionFailedError e){assertion=true;}assertTrue(assertion);set=new TreeSet<KeyValue>(new KeyValue.MetaComparator());for (int i=0;i < keys.length;i++){set.add(keys[i]);}count=0;for (KeyValue k:set){assertTrue(count++ == k.getTimestamp());}KeyValue[] rootKeys={new KeyValue(Bytes.toBytes(".META.,aaaaa,\u0000\u0000,0,2"),fam,qf,2,nb),new KeyValue(Bytes.toBytes(".META.,aaaaa,\u0001,0,3"),fam,qf,3,nb),new KeyValue(Bytes.toBytes(".META.,aaaaa,,0,1"),fam,qf,1,nb),new KeyValue(Bytes.toBytes(".META.,aaaaa,\u1000,0,5"),fam,qf,5,nb),new KeyValue(Bytes.toBytes(".META.,aaaaa,a,0,4"),fam,qf,4,nb),new KeyValue(Bytes.toBytes(".META.,,0"),fam,qf,0,nb)};set=new TreeSet<KeyValue>(new KeyValue.MetaComparator());for (int i=0;i < keys.length;i++){set.add(rootKeys[i]);}assertion=false;count=0;try {for (KeyValue k:set){assertTrue(count++ == k.getTimestamp());}} catch (junit.framework.AssertionFailedError e){assertion=true;}set=new TreeSet<KeyValue>(new KeyValue.RootComparator());for (int i=0;i < keys.length;i++){set.add(rootKeys[i]);}count=0;for (KeyValue k:set){assertTrue(count++ == k.getTimestamp());}}
}
