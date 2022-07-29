/**
 * Copyright 2007 The Apache Software Foundation
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseTestCase;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.regionserver.wal.HLog;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.hfile.HFileScanner;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hdfs.MiniDFSCluster;


/**
 * Test compactions
 */
public class TestCompaction extends HBaseTestCase {
  static final Log LOG = LogFactory.getLog(TestCompaction.class.getName());
  private HRegion r = null;
  private Path compactionDir = null;
  private Path regionCompactionDir = null;
  private static final byte [] COLUMN_FAMILY = fam1;
  private final byte [] STARTROW = Bytes.toBytes(START_KEY);
  private static final byte [] COLUMN_FAMILY_TEXT = COLUMN_FAMILY;
  private static final int COMPACTION_THRESHOLD = MAXVERSIONS;

  private MiniDFSCluster cluster;

  /**
 * Test that on a major compaction, if all cells are expired or deleted, then we'll end up with no product.  Make sure scanner over region returns right answer in this case - and that it just basically works.
 * @throws IOException
 */public void testMajorCompactingToNoOutput() throws IOException{createStoreFile(r);for (int i=0;i < COMPACTION_THRESHOLD;i++){createStoreFile(r);}InternalScanner s=r.getScanner(new Scan());do {List<KeyValue> results=new ArrayList<KeyValue>();boolean result=s.next(results);r.delete(new Delete(results.get(0).getRow()),null,false);if (!result)break;} while (true);r.flushcache();r.compactStores(true);s=r.getScanner(new Scan());int counter=0;do {List<KeyValue> results=new ArrayList<KeyValue>();boolean result=s.next(results);if (!result)break;counter++;} while (true);assertEquals(0,counter);}

  private int count() throws IOException {
    int count = 0;
    for (StoreFile f: this.r.stores.
        get(COLUMN_FAMILY_TEXT).getStorefiles().values()) {
      HFileScanner scanner = f.getReader().getScanner(false, false);
      if (!scanner.seekTo()) {
        continue;
      }
      do {
        count++;
      } while(scanner.next());
    }
    return count;
  }

  private void createStoreFile(final HRegion region) throws IOException {
    HRegionIncommon loader = new HRegionIncommon(region);
    addContent(loader, Bytes.toString(COLUMN_FAMILY));
    loader.flushcache();
  }

  private void createSmallerStoreFile(final HRegion region) throws IOException {
    HRegionIncommon loader = new HRegionIncommon(region);
    addContent(loader, Bytes.toString(COLUMN_FAMILY), ("" +
    		"bbb").getBytes(), null);
    loader.flushcache();
  }
}
