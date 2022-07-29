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
package org.apache.hadoop.hbase.regionserver;

import java.util.Iterator;
import java.util.SortedSet;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.util.Bytes;

import junit.framework.TestCase;

public class TestKeyValueSkipListSet extends TestCase {
  private final KeyValueSkipListSet kvsls =
    new KeyValueSkipListSet(KeyValue.COMPARATOR);

  protected void setUp() throws Exception {
    super.setUp();
    this.kvsls.clear();
  }

  public void testDescendingIterator() throws Exception{byte[] bytes=Bytes.toBytes(getName());byte[] value1=Bytes.toBytes("1");byte[] value2=Bytes.toBytes("2");final int total=3;for (int i=0;i < total;i++){this.kvsls.add(new KeyValue(bytes,bytes,Bytes.toBytes("" + i),value1));}int count=0;for (Iterator<KeyValue> i=this.kvsls.descendingIterator();i.hasNext();){KeyValue kv=i.next();assertEquals("" + (total - (count + 1)),Bytes.toString(kv.getQualifier()));assertTrue(Bytes.equals(kv.getValue(),value1));count++;}assertEquals(total,count);for (int i=0;i < total;i++){this.kvsls.add(new KeyValue(bytes,bytes,Bytes.toBytes("" + i),value2));}count=0;for (Iterator<KeyValue> i=this.kvsls.descendingIterator();i.hasNext();){KeyValue kv=i.next();assertEquals("" + (total - (count + 1)),Bytes.toString(kv.getQualifier()));assertTrue(Bytes.equals(kv.getValue(),value2));count++;}assertEquals(total,count);}
}