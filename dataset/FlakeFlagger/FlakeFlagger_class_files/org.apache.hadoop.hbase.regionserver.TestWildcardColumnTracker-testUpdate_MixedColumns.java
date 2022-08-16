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

  public void testUpdate_MixedColumns(){if (PRINT){System.out.println("\nUpdate_NewColumns");}byte[] col0=Bytes.toBytes("col0");byte[] col1=Bytes.toBytes("col1");byte[] col2=Bytes.toBytes("col2");byte[] col3=Bytes.toBytes("col3");byte[] col4=Bytes.toBytes("col4");byte[] col5=Bytes.toBytes("col5");byte[] col6=Bytes.toBytes("col6");byte[] col7=Bytes.toBytes("col7");byte[] col8=Bytes.toBytes("col8");byte[] col9=Bytes.toBytes("col9");List<MatchCode> expected=new ArrayList<MatchCode>();int size=5;for (int i=0;i < size;i++){expected.add(MatchCode.INCLUDE);}for (int i=0;i < size;i++){expected.add(MatchCode.SKIP);}for (int i=0;i < size;i++){expected.add(MatchCode.INCLUDE);}for (int i=0;i < size;i++){expected.add(MatchCode.SKIP);}int maxVersions=1;ColumnTracker wild=new WildcardColumnTracker(maxVersions);List<byte[]> scanner=new ArrayList<byte[]>();scanner.add(col0);scanner.add(col2);scanner.add(col4);scanner.add(col6);scanner.add(col8);List<MatchCode> result=new ArrayList<MatchCode>();for (int i=0;i < 2;i++){for (byte[] col:scanner){result.add(wild.checkColumn(col,0,col.length));}wild.update();}List<byte[]> scanner1=new ArrayList<byte[]>();scanner1.add(col1);scanner1.add(col3);scanner1.add(col5);scanner1.add(col7);scanner1.add(col9);for (byte[] col:scanner1){result.add(wild.checkColumn(col,0,col.length));}wild.update();for (byte[] col:scanner){result.add(wild.checkColumn(col,0,col.length));}assertEquals(expected.size(),result.size());for (int i=0;i < expected.size();i++){assertEquals(expected.get(i),result.get(i));if (PRINT){System.out.println("Expected " + expected.get(i) + ", actual " + result.get(i));}}}



}
