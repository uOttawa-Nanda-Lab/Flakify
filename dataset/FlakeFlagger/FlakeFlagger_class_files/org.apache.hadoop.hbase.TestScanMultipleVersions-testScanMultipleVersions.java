/**
 * Copyright 2008 The Apache Software Foundation
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

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.regionserver.HRegion;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Regression test for HBASE-613
 */
public class TestScanMultipleVersions extends HBaseClusterTestCase {
  private final byte[] TABLE_NAME = Bytes.toBytes("TestScanMultipleVersions");
  private final HRegionInfo[] INFOS = new HRegionInfo[2];
  private final HRegion[] REGIONS = new HRegion[2];
  private final byte[][] ROWS = new byte[][] {
      Bytes.toBytes("row_0200"),
      Bytes.toBytes("row_0800")
  };
  private final long[] TIMESTAMPS = new long[] {
      100L,
      1000L
  };
  private HTableDescriptor desc = null;

  /**
 * @throws Exception
 */public void testScanMultipleVersions() throws Exception{HTable t=new HTable(conf,TABLE_NAME);for (int i=0;i < ROWS.length;i++){for (int j=0;j < TIMESTAMPS.length;j++){Get get=new Get(ROWS[i]);get.addFamily(HConstants.CATALOG_FAMILY);get.setTimeStamp(TIMESTAMPS[j]);Result result=t.get(get);int cellCount=0;for (@SuppressWarnings("unused") KeyValue kv:result.sorted()){cellCount++;}assertTrue(cellCount == 1);}}int count=0;Scan scan=new Scan();scan.addFamily(HConstants.CATALOG_FAMILY);ResultScanner s=t.getScanner(scan);try {for (Result rr=null;(rr=s.next()) != null;){System.out.println(rr.toString());count+=1;}assertEquals("Number of rows should be 2",2,count);}  finally {s.close();}count=0;scan=new Scan();scan.setTimeRange(1000L,Long.MAX_VALUE);scan.addFamily(HConstants.CATALOG_FAMILY);s=t.getScanner(scan);try {while (s.next() != null){count+=1;}assertEquals("Number of rows should be 2",2,count);}  finally {s.close();}count=0;scan=new Scan();scan.setTimeStamp(1000L);scan.addFamily(HConstants.CATALOG_FAMILY);s=t.getScanner(scan);try {while (s.next() != null){count+=1;}assertEquals("Number of rows should be 2",2,count);}  finally {s.close();}count=0;scan=new Scan();scan.setTimeRange(100L,1000L);scan.addFamily(HConstants.CATALOG_FAMILY);s=t.getScanner(scan);try {while (s.next() != null){count+=1;}assertEquals("Number of rows should be 2",2,count);}  finally {s.close();}count=0;scan=new Scan();scan.setTimeStamp(100L);scan.addFamily(HConstants.CATALOG_FAMILY);s=t.getScanner(scan);try {while (s.next() != null){count+=1;}assertEquals("Number of rows should be 2",2,count);}  finally {s.close();}}
}