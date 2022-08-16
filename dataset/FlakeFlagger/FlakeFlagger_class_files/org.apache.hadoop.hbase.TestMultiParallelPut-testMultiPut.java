/*
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

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;
import java.util.ArrayList;

public class TestMultiParallelPut extends MultiRegionTable {
  private static final byte[] VALUE = Bytes.toBytes("value");
  private static final byte[] QUALIFIER = Bytes.toBytes("qual");
  private static final String FAMILY = "family";
  private static final String TEST_TABLE = "test_table";
  private static final byte[] BYTES_FAMILY = Bytes.toBytes(FAMILY);


  private void makeKeys() {
    for (byte [] k : KEYS) {
      byte [] cp = new byte[k.length+1];
      System.arraycopy(k, 0, cp, 0, k.length);
      cp[k.length] = 1;

      keys.add(cp);
    }
  }

  List<byte[]> keys = new ArrayList<byte[]>();

  public void testMultiPut() throws Exception{HTable table=new HTable(TEST_TABLE);table.setAutoFlush(false);table.setWriteBufferSize(10 * 1024 * 1024);for (byte[] k:keys){Put put=new Put(k);put.add(BYTES_FAMILY,QUALIFIER,VALUE);table.put(put);}table.flushCommits();for (byte[] k:keys){Get get=new Get(k);get.addColumn(BYTES_FAMILY,QUALIFIER);Result r=table.get(get);assertTrue(r.containsColumn(BYTES_FAMILY,QUALIFIER));assertEquals(0,Bytes.compareTo(VALUE,r.getValue(BYTES_FAMILY,QUALIFIER)));}HBaseAdmin admin=new HBaseAdmin(conf);ClusterStatus cs=admin.getClusterStatus();assertEquals(2,cs.getServers());for (HServerInfo info:cs.getServerInfo()){System.out.println(info);assertTrue(info.getLoad().getNumberOfRegions() > 10);}}
}
