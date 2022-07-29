/**
 * Copyright 2007 The Apache Software Foundation
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
package org.apache.hadoop.hbase.regionserver.wal;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.hadoop.hbase.HBaseClusterTestCase;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.regionserver.wal.HLog;
import org.apache.hadoop.hbase.regionserver.HRegionServer;
import org.apache.hadoop.hbase.regionserver.HRegion;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hdfs.DFSClient;
import org.apache.hadoop.hdfs.MiniDFSCluster.DataNodeProperties;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.hadoop.hdfs.server.datanode.DataNode;
import org.apache.hadoop.hdfs.server.namenode.FSNamesystem;
import org.apache.hadoop.hdfs.server.namenode.LeaseManager;
import org.apache.log4j.Level;

/**
 * Test log deletion as logs are rolled.
 */
public class TestLogRolling extends HBaseClusterTestCase {
  private static final Log LOG = LogFactory.getLog(TestLogRolling.class);
  private HRegionServer server;
  private HLog log;
  private String tableName;
  private byte[] value;

 // verbose logging on classes that are touched in these tests
 {
   ((Log4JLogger)DataNode.LOG).getLogger().setLevel(Level.ALL);
   ((Log4JLogger)LeaseManager.LOG).getLogger().setLevel(Level.ALL);
   ((Log4JLogger)FSNamesystem.LOG).getLogger().setLevel(Level.ALL);
   ((Log4JLogger)DFSClient.LOG).getLogger().setLevel(Level.ALL);
   ((Log4JLogger)HRegionServer.LOG).getLogger().setLevel(Level.ALL);
   ((Log4JLogger)HRegion.LOG).getLogger().setLevel(Level.ALL);
   ((Log4JLogger)HLog.LOG).getLogger().setLevel(Level.ALL);
 }

  private void startAndWriteData() throws Exception {
    // When the META table can be opened, the region servers are running
    new HTable(conf, HConstants.META_TABLE_NAME);
    this.server = cluster.getRegionServerThreads().get(0).getRegionServer();
    this.log = server.getLog();

    // Create the test table and open it
    HTableDescriptor desc = new HTableDescriptor(tableName);
    desc.addFamily(new HColumnDescriptor(HConstants.CATALOG_FAMILY));
    HBaseAdmin admin = new HBaseAdmin(conf);
    admin.createTable(desc);
    HTable table = new HTable(conf, tableName);
    for (int i = 1; i <= 256; i++) {    // 256 writes should cause 8 log rolls
      Put put = new Put(Bytes.toBytes("row" + String.format("%1$04d", i)));
      put.add(HConstants.CATALOG_FAMILY, null, value);
      table.put(put);
      if (i % 32 == 0) {
        // After every 32 writes sleep to let the log roller run
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          // continue
        }
      }
    }
  }

  /**
 * Tests that logs are deleted
 * @throws Exception
 */public void testLogRolling() throws Exception{this.tableName=getName();try {startAndWriteData();LOG.info("after writing there are " + log.getNumLogFiles() + " log files");List<HRegion> regions=new ArrayList<HRegion>(server.getOnlineRegions());for (HRegion r:regions){r.flushcache();}log.rollWriter();int count=log.getNumLogFiles();LOG.info("after flushing all regions and rolling logs there are " + log.getNumLogFiles() + " log files");assertTrue(("actual count: " + count),count <= 2);} catch (Exception e){LOG.fatal("unexpected exception",e);throw e;}}

  void writeData(HTable table, int rownum) throws Exception {
    Put put = new Put(Bytes.toBytes("row" + String.format("%1$04d", rownum)));
    put.add(HConstants.CATALOG_FAMILY, null, value);
    table.put(put);

    // sleep to let the log roller run (if it needs to)
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      // continue
    }
  }
}
