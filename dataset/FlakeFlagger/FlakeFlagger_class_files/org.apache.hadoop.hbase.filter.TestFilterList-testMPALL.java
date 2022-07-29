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
 * Test "must pass all"
 * @throws Exception
 */public void testMPALL() throws Exception{List<Filter> filters=new ArrayList<Filter>();filters.add(new PageFilter(MAX_PAGES));filters.add(new WhileMatchFilter(new PrefixFilter(Bytes.toBytes("yyy"))));Filter filterMPALL=new FilterList(FilterList.Operator.MUST_PASS_ALL,filters);filterMPALL.reset();assertFalse(filterMPALL.filterAllRemaining());byte[] rowkey=Bytes.toBytes("yyyyyyyyy");for (int i=0;i < MAX_PAGES - 1;i++){assertFalse(filterMPALL.filterRowKey(rowkey,0,rowkey.length));KeyValue kv=new KeyValue(rowkey,rowkey,Bytes.toBytes(i),Bytes.toBytes(i));assertTrue(Filter.ReturnCode.INCLUDE == filterMPALL.filterKeyValue(kv));}filterMPALL.reset();rowkey=Bytes.toBytes("z");assertTrue(filterMPALL.filterRowKey(rowkey,0,rowkey.length));KeyValue kv=new KeyValue(rowkey,rowkey,rowkey,rowkey);assertTrue(Filter.ReturnCode.NEXT_ROW == filterMPALL.filterKeyValue(kv));assertTrue(filterMPALL.filterRow());}
}
