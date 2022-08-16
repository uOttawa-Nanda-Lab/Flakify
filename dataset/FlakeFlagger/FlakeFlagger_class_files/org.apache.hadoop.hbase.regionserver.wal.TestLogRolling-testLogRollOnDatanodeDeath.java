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
  
  /**
 * Tests that logs are rolled upon detecting datanode death Requires an HDFS jar with HDFS-826 & syncFs() support (HDFS-200)
 * @throws Exception
 */public void testLogRollOnDatanodeDeath() throws Exception{assertTrue("This test requires HLog file replication.",fs.getDefaultReplication() > 1);new HTable(conf,HConstants.META_TABLE_NAME);this.server=cluster.getRegionServer(0);this.log=server.getLog();assertTrue("Need HDFS-826 for this test",log.canGetCurReplicas());assertTrue("Need append support for this test",HLog.isAppend(conf));dfsCluster.startDataNodes(conf,1,true,null,null);dfsCluster.waitActive();assertTrue(dfsCluster.getDataNodes().size() >= fs.getDefaultReplication() + 1);String tableName=getName();HTableDescriptor desc=new HTableDescriptor(tableName);desc.addFamily(new HColumnDescriptor(HConstants.CATALOG_FAMILY));HBaseAdmin admin=new HBaseAdmin(conf);admin.createTable(desc);HTable table=new HTable(conf,tableName);table.setAutoFlush(true);long curTime=System.currentTimeMillis();long oldFilenum=log.getFilenum();assertTrue("Log should have a timestamp older than now",curTime > oldFilenum && oldFilenum != -1);writeData(table,1);assertTrue("The log shouldn't have rolled yet",oldFilenum == log.getFilenum());OutputStream stm=log.getOutputStream();Method getPipeline=null;for (Method m:stm.getClass().getDeclaredMethods()){if (m.getName().endsWith("getPipeline")){getPipeline=m;getPipeline.setAccessible(true);break;}}assertTrue("Need DFSOutputStream.getPipeline() for this test",getPipeline != null);Object repl=getPipeline.invoke(stm,new Object[]{});DatanodeInfo[] pipeline=(DatanodeInfo[])repl;assertTrue(pipeline.length == fs.getDefaultReplication());DataNodeProperties dnprop=dfsCluster.stopDataNode(pipeline[0].getName());assertTrue(dnprop != null);writeData(table,2);long newFilenum=log.getFilenum();assertTrue("Missing datanode should've triggered a log roll",newFilenum > oldFilenum && newFilenum > curTime);writeData(table,3);assertTrue("The log should not roll again.",log.getFilenum() == newFilenum);assertTrue("New log file should have the default replication",log.getLogReplication() == fs.getDefaultReplication());}
}
