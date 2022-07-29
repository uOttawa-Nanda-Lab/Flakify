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
import org.apache.hadoop.hbase.regionserver.QueryMatcher.MatchCode;
import org.apache.hadoop.hbase.util.Bytes;

public class TestScanWildcardColumnTracker extends HBaseTestCase {

  final static int VERSIONS = 2;

  public void testCheckColumn_EnforceVersions(){ScanWildcardColumnTracker tracker=new ScanWildcardColumnTracker(VERSIONS);List<byte[]> qualifiers=new ArrayList<byte[]>();qualifiers.add(Bytes.toBytes("qualifer1"));qualifiers.add(Bytes.toBytes("qualifer1"));qualifiers.add(Bytes.toBytes("qualifer1"));qualifiers.add(Bytes.toBytes("qualifer2"));List<MatchCode> expected=new ArrayList<MatchCode>();expected.add(MatchCode.INCLUDE);expected.add(MatchCode.INCLUDE);expected.add(MatchCode.SKIP);expected.add(MatchCode.INCLUDE);List<MatchCode> actual=new ArrayList<MatchCode>();for (byte[] qualifier:qualifiers){MatchCode mc=tracker.checkColumn(qualifier,0,qualifier.length);actual.add(mc);}for (int i=0;i < expected.size();i++){assertEquals(expected.get(i),actual.get(i));}}

}
