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

package org.apache.hadoop.hbase.regionserver;

import org.apache.hadoop.hbase.HBaseTestCase;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.util.Bytes;


public class TestScanDeleteTracker extends HBaseTestCase implements HConstants {

  private ScanDeleteTracker sdt;
  private long timestamp = 10L;
  private byte deleteType = 0;

  public void testDelete_KeepDelete() {
	byte[] qualifier = Bytes.toBytes("qualifier");
	deleteType = KeyValue.Type.Delete.getCode();
	sdt.add(qualifier, 0, qualifier.length, timestamp, deleteType);
	sdt.isDeleted(qualifier, 0, qualifier.length, timestamp);
	assertEquals(false, sdt.isEmpty());
}


}
