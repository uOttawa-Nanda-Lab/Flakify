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

  public void testScan() throws Exception{byte[] startRow="startRow".getBytes();byte[] stopRow="stopRow".getBytes();byte[] fam="fam".getBytes();byte[] qf1="qf1".getBytes();long ts=System.currentTimeMillis();int maxVersions=2;Scan scan=new Scan(startRow,stopRow);scan.addColumn(fam,qf1);scan.setTimeRange(ts,ts + 1);scan.setMaxVersions(maxVersions);byte[] sb=Writables.getBytes(scan);Scan desScan=(Scan)Writables.getWritable(sb,new Scan());assertTrue(Bytes.equals(scan.getStartRow(),desScan.getStartRow()));assertTrue(Bytes.equals(scan.getStopRow(),desScan.getStopRow()));assertEquals(scan.getCacheBlocks(),desScan.getCacheBlocks());Set<byte[]> set=null;Set<byte[]> desSet=null;for (Map.Entry<byte[], NavigableSet<byte[]>> entry:scan.getFamilyMap().entrySet()){assertTrue(desScan.getFamilyMap().containsKey(entry.getKey()));set=entry.getValue();desSet=desScan.getFamilyMap().get(entry.getKey());for (byte[] column:set){assertTrue(desSet.contains(column));}scan=new Scan(startRow);byte[] prefix=Bytes.toBytes(getName());scan.setFilter(new PrefixFilter(prefix));sb=Writables.getBytes(scan);desScan=(Scan)Writables.getWritable(sb,new Scan());Filter f=desScan.getFilter();assertTrue(f instanceof PrefixFilter);}assertEquals(scan.getMaxVersions(),desScan.getMaxVersions());TimeRange tr=scan.getTimeRange();TimeRange desTr=desScan.getTimeRange();assertEquals(tr.getMax(),desTr.getMax());assertEquals(tr.getMin(),desTr.getMin());}
}