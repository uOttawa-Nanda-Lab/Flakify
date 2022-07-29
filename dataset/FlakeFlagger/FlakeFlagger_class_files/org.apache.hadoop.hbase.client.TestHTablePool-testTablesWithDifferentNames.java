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

  @Test public void testTablesWithDifferentNames(){HTablePool pool=new HTablePool((HBaseConfiguration)null,Integer.MAX_VALUE);String tableName1="testTable1";String tableName2="testTable2";HTableInterface table1=pool.getTable(tableName1);HTableInterface table2=pool.getTable(tableName2);Assert.assertNotNull(table2);pool.putTable(table1);pool.putTable(table2);HTableInterface sameTable1=pool.getTable(tableName1);HTableInterface sameTable2=pool.getTable(tableName2);Assert.assertSame(table1,sameTable1);Assert.assertSame(table2,sameTable2);}
}
