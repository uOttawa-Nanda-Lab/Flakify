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

  @Test public void testCreateTableWithRegions() throws IOException{byte[] tableName=Bytes.toBytes("testCreateTableWithRegions");byte[][] splitKeys={new byte[]{1,1,1},new byte[]{2,2,2},new byte[]{3,3,3},new byte[]{4,4,4},new byte[]{5,5,5},new byte[]{6,6,6},new byte[]{7,7,7},new byte[]{8,8,8},new byte[]{9,9,9}};int expectedRegions=splitKeys.length + 1;HTableDescriptor desc=new HTableDescriptor(tableName);desc.addFamily(new HColumnDescriptor(HConstants.CATALOG_FAMILY));admin.createTable(desc,splitKeys);HTable ht=new HTable(TEST_UTIL.getConfiguration(),tableName);Map<HRegionInfo, HServerAddress> regions=ht.getRegionsInfo();assertEquals("Tried to create " + expectedRegions + " regions " + "but only found " + regions.size(),expectedRegions,regions.size());System.err.println("Found " + regions.size() + " regions");Iterator<HRegionInfo> hris=regions.keySet().iterator();HRegionInfo hri=hris.next();assertTrue(hri.getStartKey() == null || hri.getStartKey().length == 0);assertTrue(Bytes.equals(hri.getEndKey(),splitKeys[0]));hri=hris.next();assertTrue(Bytes.equals(hri.getStartKey(),splitKeys[0]));assertTrue(Bytes.equals(hri.getEndKey(),splitKeys[1]));hri=hris.next();assertTrue(Bytes.equals(hri.getStartKey(),splitKeys[1]));assertTrue(Bytes.equals(hri.getEndKey(),splitKeys[2]));hri=hris.next();assertTrue(Bytes.equals(hri.getStartKey(),splitKeys[2]));assertTrue(Bytes.equals(hri.getEndKey(),splitKeys[3]));hri=hris.next();assertTrue(Bytes.equals(hri.getStartKey(),splitKeys[3]));assertTrue(Bytes.equals(hri.getEndKey(),splitKeys[4]));hri=hris.next();assertTrue(Bytes.equals(hri.getStartKey(),splitKeys[4]));assertTrue(Bytes.equals(hri.getEndKey(),splitKeys[5]));hri=hris.next();assertTrue(Bytes.equals(hri.getStartKey(),splitKeys[5]));assertTrue(Bytes.equals(hri.getEndKey(),splitKeys[6]));hri=hris.next();assertTrue(Bytes.equals(hri.getStartKey(),splitKeys[6]));assertTrue(Bytes.equals(hri.getEndKey(),splitKeys[7]));hri=hris.next();assertTrue(Bytes.equals(hri.getStartKey(),splitKeys[7]));assertTrue(Bytes.equals(hri.getEndKey(),splitKeys[8]));hri=hris.next();assertTrue(Bytes.equals(hri.getStartKey(),splitKeys[8]));assertTrue(hri.getEndKey() == null || hri.getEndKey().length == 0);byte[] startKey={1,1,1,1,1,1,1,1,1,1};byte[] endKey={9,9,9,9,9,9,9,9,9,9};expectedRegions=10;byte[] TABLE_2=Bytes.add(tableName,Bytes.toBytes("_2"));desc=new HTableDescriptor(TABLE_2);desc.addFamily(new HColumnDescriptor(HConstants.CATALOG_FAMILY));admin=new HBaseAdmin(TEST_UTIL.getConfiguration());admin.createTable(desc,startKey,endKey,expectedRegions);ht=new HTable(TEST_UTIL.getConfiguration(),TABLE_2);regions=ht.getRegionsInfo();assertEquals("Tried to create " + expectedRegions + " regions " + "but only found " + regions.size(),expectedRegions,regions.size());System.err.println("Found " + regions.size() + " regions");hris=regions.keySet().iterator();hri=hris.next();assertTrue(hri.getStartKey() == null || hri.getStartKey().length == 0);assertTrue(Bytes.equals(hri.getEndKey(),new byte[]{1,1,1,1,1,1,1,1,1,1}));hri=hris.next();assertTrue(Bytes.equals(hri.getStartKey(),new byte[]{1,1,1,1,1,1,1,1,1,1}));assertTrue(Bytes.equals(hri.getEndKey(),new byte[]{2,2,2,2,2,2,2,2,2,2}));hri=hris.next();assertTrue(Bytes.equals(hri.getStartKey(),new byte[]{2,2,2,2,2,2,2,2,2,2}));assertTrue(Bytes.equals(hri.getEndKey(),new byte[]{3,3,3,3,3,3,3,3,3,3}));hri=hris.next();assertTrue(Bytes.equals(hri.getStartKey(),new byte[]{3,3,3,3,3,3,3,3,3,3}));assertTrue(Bytes.equals(hri.getEndKey(),new byte[]{4,4,4,4,4,4,4,4,4,4}));hri=hris.next();assertTrue(Bytes.equals(hri.getStartKey(),new byte[]{4,4,4,4,4,4,4,4,4,4}));assertTrue(Bytes.equals(hri.getEndKey(),new byte[]{5,5,5,5,5,5,5,5,5,5}));hri=hris.next();assertTrue(Bytes.equals(hri.getStartKey(),new byte[]{5,5,5,5,5,5,5,5,5,5}));assertTrue(Bytes.equals(hri.getEndKey(),new byte[]{6,6,6,6,6,6,6,6,6,6}));hri=hris.next();assertTrue(Bytes.equals(hri.getStartKey(),new byte[]{6,6,6,6,6,6,6,6,6,6}));assertTrue(Bytes.equals(hri.getEndKey(),new byte[]{7,7,7,7,7,7,7,7,7,7}));hri=hris.next();assertTrue(Bytes.equals(hri.getStartKey(),new byte[]{7,7,7,7,7,7,7,7,7,7}));assertTrue(Bytes.equals(hri.getEndKey(),new byte[]{8,8,8,8,8,8,8,8,8,8}));hri=hris.next();assertTrue(Bytes.equals(hri.getStartKey(),new byte[]{8,8,8,8,8,8,8,8,8,8}));assertTrue(Bytes.equals(hri.getEndKey(),new byte[]{9,9,9,9,9,9,9,9,9,9}));hri=hris.next();assertTrue(Bytes.equals(hri.getStartKey(),new byte[]{9,9,9,9,9,9,9,9,9,9}));assertTrue(hri.getEndKey() == null || hri.getEndKey().length == 0);startKey=new byte[]{0,0,0,0,0,0};endKey=new byte[]{1,0,0,0,0,0};expectedRegions=5;byte[] TABLE_3=Bytes.add(tableName,Bytes.toBytes("_3"));desc=new HTableDescriptor(TABLE_3);desc.addFamily(new HColumnDescriptor(HConstants.CATALOG_FAMILY));admin=new HBaseAdmin(TEST_UTIL.getConfiguration());admin.createTable(desc,startKey,endKey,expectedRegions);ht=new HTable(TEST_UTIL.getConfiguration(),TABLE_3);regions=ht.getRegionsInfo();assertEquals("Tried to create " + expectedRegions + " regions " + "but only found " + regions.size(),expectedRegions,regions.size());System.err.println("Found " + regions.size() + " regions");splitKeys=new byte[][]{new byte[]{1,1,1},new byte[]{2,2,2},new byte[]{3,3,3},new byte[]{2,2,2}};byte[] TABLE_4=Bytes.add(tableName,Bytes.toBytes("_4"));desc=new HTableDescriptor(TABLE_4);desc.addFamily(new HColumnDescriptor(HConstants.CATALOG_FAMILY));admin=new HBaseAdmin(TEST_UTIL.getConfiguration());try {admin.createTable(desc,splitKeys);assertTrue("Should not be able to create this table because of " + "duplicate split keys",false);} catch (IllegalArgumentException iae){}}
}

