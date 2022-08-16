/*
 * Copyright 2010 The Apache Software Foundation
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

package org.apache.hadoop.hbase.stargate.client;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.stargate.MiniClusterTestBase;
import org.apache.hadoop.hbase.stargate.client.Client;
import org.apache.hadoop.hbase.util.Bytes;

public class TestRemoteAdmin extends MiniClusterTestBase {

  static final String TABLE_1 = "TestRemoteAdmin_Table_1";
  static final String TABLE_2 = "TestRemoteAdmin_Table_2";
  static final byte[] COLUMN_1 = Bytes.toBytes("a");

  static final HTableDescriptor DESC_1;
  static {
    DESC_1 = new HTableDescriptor(TABLE_1);
    DESC_1.addFamily(new HColumnDescriptor(COLUMN_1));
  }
  static final HTableDescriptor DESC_2;
  static {
    DESC_2 = new HTableDescriptor(TABLE_2);
    DESC_2.addFamily(new HColumnDescriptor(COLUMN_1));
  }

  Client client;
  HBaseAdmin localAdmin;
  RemoteAdmin remoteAdmin;

  public void testCreateTable() throws Exception {
	assertFalse(remoteAdmin.isTableAvailable(TABLE_1));
	remoteAdmin.createTable(DESC_1);
	assertTrue(remoteAdmin.isTableAvailable(TABLE_1));
}

}
