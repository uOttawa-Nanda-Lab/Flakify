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

package org.apache.hadoop.hbase.stargate.util;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.stargate.MiniClusterTestBase;
import org.apache.hadoop.hbase.stargate.util.HTableTokenBucket;
import org.apache.hadoop.hbase.util.Bytes;

public class TestHTableTokenBucket extends MiniClusterTestBase {

  static final String TABLE = "users";
  static final byte[] USER = Bytes.toBytes("user");
  static final byte[] NAME = Bytes.toBytes("name");
  static final byte[] TOKENS = Bytes.toBytes("tokens");
  static final byte[] TOKENS_RATE = Bytes.toBytes("tokens.rate");
  static final byte[] TOKENS_SIZE = Bytes.toBytes("tokens.size");
  static final String USER_TOKEN = "da4829144e3a2febd909a6e1b4ed7cfa";
  static final String USER_USERNAME = "testUser";
  static final double RATE = 1; // per second
  static final long SIZE = 10;

  public void testTokenBucketConfig() throws Exception {
	HTableTokenBucket tb = new HTableTokenBucket(conf, TABLE, Bytes.toBytes(USER_TOKEN));
	assertEquals(tb.getRate(), RATE);
	assertEquals(tb.getSize(), SIZE);
}

}
