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

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.HBaseTestCase;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.regionserver.QueryMatcher.MatchCode;
import org.apache.hadoop.hbase.util.Bytes;

public class TestWildcardColumnTracker extends HBaseTestCase
implements HConstants {
  private boolean PRINT = false;

  // HBASE-1781
  public void testStackOverflow(){
    int maxVersions = 1;

    ColumnTracker wild = new WildcardColumnTracker(maxVersions);
    for(int i = 0; i < 100000; i+=2) {
      byte [] col = Bytes.toBytes("col"+i);
      wild.checkColumn(col, 0, col.length);
    }
    wild.update();

    for(int i = 1; i < 100000; i+=2) {
      byte [] col = Bytes.toBytes("col"+i);
      wild.checkColumn(col, 0, col.length);
    }
  }



}
