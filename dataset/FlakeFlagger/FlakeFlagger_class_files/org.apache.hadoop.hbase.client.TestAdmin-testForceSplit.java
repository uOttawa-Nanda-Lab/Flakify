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
package org.apache.hadoop.hbase.client;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HServerAddress;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableExistsException;
import org.apache.hadoop.hbase.TableNotDisabledException;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Class to test HBaseAdmin.
 * Spins up the minicluster once at test start and then takes it down afterward.
 * Add any testing of HBaseAdmin functionality here.
 */
public class TestAdmin {
  final Log LOG = LogFactory.getLog(getClass());
  private final static HBaseTestingUtility TEST_UTIL = new HBaseTestingUtility();
  private HBaseAdmin admin;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    TEST_UTIL.getConfiguration().setInt("hbase.regionserver.msginterval", 100);
    TEST_UTIL.getConfiguration().setInt("hbase.client.pause", 250);
    TEST_UTIL.getConfiguration().setInt("hbase.client.retries.number", 6);
    TEST_UTIL.startMiniCluster(3);
  }

  @Before
  public void setUp() throws Exception {
    this.admin = new HBaseAdmin(TEST_UTIL.getConfiguration());
  }

  /**
 * Tests forcing split from client and having scanners successfully ride over split.
 * @throws Exception
 * @throws IOException
 */@Test public void testForceSplit() throws Exception{byte[] familyName=HConstants.CATALOG_FAMILY;byte[] tableName=Bytes.toBytes("testForceSplit");final HTable table=TEST_UTIL.createTable(tableName,familyName);byte[] k=new byte[3];int rowCount=0;for (byte b1='a';b1 < 'z';b1++){for (byte b2='a';b2 < 'z';b2++){for (byte b3='a';b3 < 'z';b3++){k[0]=b1;k[1]=b2;k[2]=b3;Put put=new Put(k);put.add(familyName,new byte[0],k);table.put(put);rowCount++;}}}Map<HRegionInfo, HServerAddress> m=table.getRegionsInfo();System.out.println("Initial regions (" + m.size() + "): " + m);assertTrue(m.size() == 1);Scan scan=new Scan();ResultScanner scanner=table.getScanner(scan);int rows=0;for (@SuppressWarnings("unused") Result result:scanner){rows++;}scanner.close();assertEquals(rowCount,rows);scan=new Scan();scanner=table.getScanner(scan);scanner.next();final AtomicInteger count=new AtomicInteger(0);Thread t=new Thread("CheckForSplit"){public void run(){for (int i=0;i < 20;i++){try {sleep(1000);} catch (InterruptedException e){continue;}Map<HRegionInfo, HServerAddress> regions=null;try {regions=table.getRegionsInfo();} catch (IOException e){e.printStackTrace();}if (regions == null)continue;count.set(regions.size());if (count.get() >= 2)break;LOG.debug("Cycle waiting on split");}}};t.start();admin.split(Bytes.toString(tableName));t.join();rows=1;for (@SuppressWarnings("unused") Result result:scanner){rows++;if (rows > rowCount){scanner.close();assertTrue("Scanned more than expected (" + rowCount + ")",false);}}scanner.close();assertEquals(rowCount,rows);}
}

