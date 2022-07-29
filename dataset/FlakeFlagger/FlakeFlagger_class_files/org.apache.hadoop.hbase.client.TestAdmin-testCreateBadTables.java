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

  @Test public void testCreateBadTables() throws IOException{String msg=null;try {this.admin.createTable(HTableDescriptor.ROOT_TABLEDESC);} catch (IllegalArgumentException e){msg=e.toString();}assertTrue("Unexcepted exception message " + msg,msg != null && msg.startsWith(IllegalArgumentException.class.getName()) && msg.contains(HTableDescriptor.ROOT_TABLEDESC.getNameAsString()));msg=null;try {this.admin.createTable(HTableDescriptor.META_TABLEDESC);} catch (IllegalArgumentException e){msg=e.toString();}assertTrue("Unexcepted exception message " + msg,msg != null && msg.startsWith(IllegalArgumentException.class.getName()) && msg.contains(HTableDescriptor.META_TABLEDESC.getNameAsString()));final HTableDescriptor threadDesc=new HTableDescriptor("threaded_testCreateBadTables");threadDesc.addFamily(new HColumnDescriptor(HConstants.CATALOG_FAMILY));int count=10;Thread[] threads=new Thread[count];final AtomicInteger successes=new AtomicInteger(0);final AtomicInteger failures=new AtomicInteger(0);final HBaseAdmin localAdmin=this.admin;for (int i=0;i < count;i++){threads[i]=new Thread(Integer.toString(i)){@Override public void run(){try {localAdmin.createTable(threadDesc);successes.incrementAndGet();} catch (TableExistsException e){failures.incrementAndGet();}catch (IOException e){throw new RuntimeException("Failed threaded create" + getName(),e);}}};}for (int i=0;i < count;i++){threads[i].start();}for (int i=0;i < count;i++){while (threads[i].isAlive()){try {Thread.sleep(1000);} catch (InterruptedException e){}}}assertEquals(1,successes.get());assertEquals(count - 1,failures.get());}
}

