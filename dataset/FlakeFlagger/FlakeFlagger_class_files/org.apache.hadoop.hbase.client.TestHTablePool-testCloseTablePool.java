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

import java.io.IOException;

import junit.framework.Assert;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests HTablePool.
 */
public class TestHTablePool  {

  private static HBaseTestingUtility TEST_UTIL   =  new HBaseTestingUtility();

  @BeforeClass
  public static void beforeClass() throws Exception {
    TEST_UTIL.startMiniCluster(1);

  }

  @Test public void testCloseTablePool() throws IOException{HTablePool pool=new HTablePool(TEST_UTIL.getConfiguration(),4);String tableName="testTable";HBaseAdmin admin=new HBaseAdmin(TEST_UTIL.getConfiguration());if (admin.tableExists(tableName)){admin.deleteTable(tableName);}HTableDescriptor tableDescriptor=new HTableDescriptor(Bytes.toBytes(tableName));tableDescriptor.addFamily(new HColumnDescriptor("randomFamily"));admin.createTable(tableDescriptor);HTableInterface[] tables=new HTableInterface[4];for (int i=0;i < 4;++i){tables[i]=pool.getTable(tableName);}pool.closeTablePool(tableName);for (int i=0;i < 4;++i){pool.putTable(tables[i]);}Assert.assertEquals(4,pool.getCurrentPoolSize(tableName));pool.closeTablePool(tableName);Assert.assertEquals(0,pool.getCurrentPoolSize(tableName));}
}
