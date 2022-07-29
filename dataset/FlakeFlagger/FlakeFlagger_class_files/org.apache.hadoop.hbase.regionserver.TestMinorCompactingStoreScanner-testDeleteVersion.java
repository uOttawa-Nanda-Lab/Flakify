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

import junit.framework.TestCase;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.KeyValueTestUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestMinorCompactingStoreScanner extends TestCase {

  public void testDeleteVersion() throws IOException{KeyValue[] kvs=new KeyValue[]{KeyValueTestUtil.create("R1","cf","a",15,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","a",10,KeyValue.Type.Delete,"dont-care"),KeyValueTestUtil.create("R1","cf","a",10,KeyValue.Type.Put,"dont-care")};KeyValueScanner[] scanners=new KeyValueScanner[]{new KeyValueScanFixture(KeyValue.COMPARATOR,kvs)};InternalScanner scan=new MinorCompactingStoreScanner("cf",KeyValue.COMPARATOR,scanners);List<KeyValue> results=new ArrayList<KeyValue>();assertFalse(scan.next(results));assertEquals(3,results.size());assertEquals(kvs[0],results.get(0));assertEquals(kvs[1],results.get(1));assertEquals(kvs[2],results.get(2));}
}
