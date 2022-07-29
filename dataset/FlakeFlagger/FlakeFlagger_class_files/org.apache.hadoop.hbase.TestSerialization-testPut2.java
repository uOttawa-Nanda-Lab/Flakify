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


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;

import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.RowLock;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.io.HbaseMapWritable;
import org.apache.hadoop.hbase.io.TimeRange;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.Writables;
import org.apache.hadoop.io.DataInputBuffer;

/**
 * Test HBase Writables serializations
 */
public class TestSerialization extends HBaseTestCase {

  public void testPut2() throws Exception{byte[] row="testAbort,,1243116656250".getBytes();byte[] fam="historian".getBytes();byte[] qf1="creation".getBytes();long ts=9223372036854775807L;byte[] val="dont-care".getBytes();Put put=new Put(row);put.add(fam,qf1,ts,val);byte[] sb=Writables.getBytes(put);Put desPut=(Put)Writables.getWritable(sb,new Put());assertTrue(Bytes.equals(put.getRow(),desPut.getRow()));List<KeyValue> list=null;List<KeyValue> desList=null;for (Map.Entry<byte[], List<KeyValue>> entry:put.getFamilyMap().entrySet()){assertTrue(desPut.getFamilyMap().containsKey(entry.getKey()));list=entry.getValue();desList=desPut.getFamilyMap().get(entry.getKey());for (int i=0;i < list.size();i++){assertTrue(list.get(i).equals(desList.get(i)));}}}
}