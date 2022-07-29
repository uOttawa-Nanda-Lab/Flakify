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
package org.apache.hadoop.hbase.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.KeyValue;


import junit.framework.TestCase;

/**
 * Tests filter sets
 *
 */
public class TestFilterList extends TestCase {
  static final int MAX_PAGES = 2;
  static final char FIRST_CHAR = 'a';
  static final char LAST_CHAR = 'e';
  static byte[] GOOD_BYTES = Bytes.toBytes("abc");
  static byte[] BAD_BYTES = Bytes.toBytes("def");

  /**
 * Test list ordering
 * @throws Exception
 */public void testOrdering() throws Exception{List<Filter> filters=new ArrayList<Filter>();filters.add(new PrefixFilter(Bytes.toBytes("yyy")));filters.add(new PageFilter(MAX_PAGES));Filter filterMPONE=new FilterList(FilterList.Operator.MUST_PASS_ONE,filters);filterMPONE.reset();assertFalse(filterMPONE.filterAllRemaining());byte[] rowkey=Bytes.toBytes("yyyyyyyy");for (int i=0;i < MAX_PAGES;i++){assertFalse(filterMPONE.filterRowKey(rowkey,0,rowkey.length));KeyValue kv=new KeyValue(rowkey,rowkey,Bytes.toBytes(i),Bytes.toBytes(i));assertTrue(Filter.ReturnCode.INCLUDE == filterMPONE.filterKeyValue(kv));assertFalse(filterMPONE.filterRow());}rowkey=Bytes.toBytes("xxxxxxx");for (int i=0;i < MAX_PAGES;i++){assertFalse(filterMPONE.filterRowKey(rowkey,0,rowkey.length));KeyValue kv=new KeyValue(rowkey,rowkey,Bytes.toBytes(i),Bytes.toBytes(i));assertTrue(Filter.ReturnCode.INCLUDE == filterMPONE.filterKeyValue(kv));assertFalse(filterMPONE.filterRow());}rowkey=Bytes.toBytes("yyy");for (int i=0;i < MAX_PAGES;i++){assertFalse(filterMPONE.filterRowKey(rowkey,0,rowkey.length));KeyValue kv=new KeyValue(rowkey,rowkey,Bytes.toBytes(i),Bytes.toBytes(i));assertTrue(Filter.ReturnCode.INCLUDE == filterMPONE.filterKeyValue(kv));assertFalse(filterMPONE.filterRow());}}
}
