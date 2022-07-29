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

  public void testAdd() throws Exception {
	byte[] bytes = Bytes.toBytes(getName());
	KeyValue kv = new KeyValue(bytes, bytes, bytes, bytes);
	this.kvsls.add(kv);
	assertTrue(this.kvsls.contains(kv));
	assertEquals(1, this.kvsls.size());
	KeyValue first = this.kvsls.first();
	assertTrue(kv.equals(first));
	assertTrue(Bytes.equals(kv.getValue(), first.getValue()));
	byte[] overwriteValue = Bytes.toBytes("overwrite");
	KeyValue overwrite = new KeyValue(bytes, bytes, bytes, overwriteValue);
	this.kvsls.add(overwrite);
	assertEquals(1, this.kvsls.size());
	first = this.kvsls.first();
	assertTrue(Bytes.equals(overwrite.getValue(), first.getValue()));
	assertFalse(Bytes.equals(overwrite.getValue(), kv.getValue()));
}
}