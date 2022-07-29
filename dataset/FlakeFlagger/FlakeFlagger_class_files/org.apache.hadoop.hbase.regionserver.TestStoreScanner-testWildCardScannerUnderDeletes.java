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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

import junit.framework.TestCase;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.KeyValueTestUtil;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class TestStoreScanner extends TestCase {
  private static final String CF_STR = "cf";
  final byte [] CF = Bytes.toBytes(CF_STR);

  /**
   * Test utility for building a NavigableSet for scanners.
   * @param strCols
   * @return
   */
  NavigableSet<byte[]> getCols(String ...strCols) {
    NavigableSet<byte[]> cols = new TreeSet<byte[]>(Bytes.BYTES_COMPARATOR);
    for (String col : strCols) {
      byte[] bytes = Bytes.toBytes(col);
      cols.add(bytes);
    }
    return cols;
  }

  public void testWildCardScannerUnderDeletes() throws IOException{KeyValue[] kvs=new KeyValue[]{KeyValueTestUtil.create("R1","cf","a",2,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","a",1,KeyValue.Type.DeleteColumn,"dont-care"),KeyValueTestUtil.create("R1","cf","b",2,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","b",1,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","c",10,KeyValue.Type.Delete,"dont-care"),KeyValueTestUtil.create("R1","cf","c",10,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","c",9,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","d",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","d",10,KeyValue.Type.DeleteColumn,"dont-care"),KeyValueTestUtil.create("R1","cf","d",9,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","d",8,KeyValue.Type.Put,"dont-care")};KeyValueScanner[] scanners=new KeyValueScanner[]{new KeyValueScanFixture(KeyValue.COMPARATOR,kvs)};StoreScanner scan=new StoreScanner(new Scan().setMaxVersions(2),CF,Long.MAX_VALUE,KeyValue.COMPARATOR,null,scanners);List<KeyValue> results=new ArrayList<KeyValue>();assertEquals(true,scan.next(results));assertEquals(5,results.size());assertEquals(kvs[0],results.get(0));assertEquals(kvs[2],results.get(1));assertEquals(kvs[3],results.get(2));assertEquals(kvs[6],results.get(3));assertEquals(kvs[7],results.get(4));}
}
