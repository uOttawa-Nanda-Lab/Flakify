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
 * Test "must pass one"
 * @throws Exception
 */public void testMPONE() throws Exception{List<Filter> filters=new ArrayList<Filter>();filters.add(new PageFilter(MAX_PAGES));filters.add(new WhileMatchFilter(new PrefixFilter(Bytes.toBytes("yyy"))));Filter filterMPONE=new FilterList(FilterList.Operator.MUST_PASS_ONE,filters);filterMPONE.reset();assertFalse(filterMPONE.filterAllRemaining());byte[] rowkey=Bytes.toBytes("yyyyyyyyy");for (int i=0;i < MAX_PAGES - 1;i++){assertFalse(filterMPONE.filterRowKey(rowkey,0,rowkey.length));assertFalse(filterMPONE.filterRow());KeyValue kv=new KeyValue(rowkey,rowkey,Bytes.toBytes(i),Bytes.toBytes(i));assertTrue(Filter.ReturnCode.INCLUDE == filterMPONE.filterKeyValue(kv));}rowkey=Bytes.toBytes("z");assertFalse(filterMPONE.filterRowKey(rowkey,0,rowkey.length));assertFalse(filterMPONE.filterRow());KeyValue kv=new KeyValue(rowkey,rowkey,Bytes.toBytes(0),Bytes.toBytes(0));assertTrue(Filter.ReturnCode.INCLUDE == filterMPONE.filterKeyValue(kv));rowkey=Bytes.toBytes("yyy");assertFalse(filterMPONE.filterRowKey(rowkey,0,rowkey.length));assertFalse(filterMPONE.filterRow());kv=new KeyValue(rowkey,rowkey,Bytes.toBytes(0),Bytes.toBytes(0));assertTrue(Filter.ReturnCode.INCLUDE == filterMPONE.filterKeyValue(kv));rowkey=Bytes.toBytes("z");assertTrue(filterMPONE.filterRowKey(rowkey,0,rowkey.length));assertTrue(filterMPONE.filterRow());assertTrue(filterMPONE.filterAllRemaining());}
}
