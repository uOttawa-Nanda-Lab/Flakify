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

  public void testHeadTail() throws Exception{byte[] bytes=Bytes.toBytes(getName());byte[] value1=Bytes.toBytes("1");byte[] value2=Bytes.toBytes("2");final int total=3;KeyValue splitter=null;for (int i=0;i < total;i++){KeyValue kv=new KeyValue(bytes,bytes,Bytes.toBytes("" + i),value1);if (i == 1)splitter=kv;this.kvsls.add(kv);}SortedSet<KeyValue> tail=this.kvsls.tailSet(splitter);assertEquals(2,tail.size());SortedSet<KeyValue> head=this.kvsls.headSet(splitter);assertEquals(1,head.size());for (int i=0;i < total;i++){this.kvsls.add(new KeyValue(bytes,bytes,Bytes.toBytes("" + i),value2));}tail=this.kvsls.tailSet(splitter);assertTrue(Bytes.equals(tail.first().getValue(),value2));head=this.kvsls.headSet(splitter);assertTrue(Bytes.equals(head.first().getValue(),value2));}
}