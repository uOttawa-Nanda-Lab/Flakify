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
package org.apache.hadoop.hbase.regionserver.wal;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseTestCase;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.regionserver.wal.HLog.Reader;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hdfs.MiniDFSCluster;

/** JUnit test case for HLog */
public class TestHLog extends HBaseTestCase implements HConstants {
  private Path dir;
  private Path oldLogDir;
  private MiniDFSCluster cluster;

  private void verifySplits(List<Path> splits, final int howmany)
  throws IOException {
    assertEquals(howmany, splits.size());
    for (int i = 0; i < splits.size(); i++) {
      HLog.Reader reader = HLog.getReader(this.fs, splits.get(i), conf);
      try {
        int count = 0;
        String previousRegion = null;
        long seqno = -1;
        HLog.Entry entry = new HLog.Entry();
        while((entry = reader.next(entry)) != null) {
          HLogKey key = entry.getKey();
          WALEdit kv = entry.getEdit();
          String region = Bytes.toString(key.getRegionName());
          // Assert that all edits are for same region.
          if (previousRegion != null) {
            assertEquals(previousRegion, region);
          }
          assertTrue(seqno < key.getLogSeqNum());
          seqno = key.getLogSeqNum();
          previousRegion = region;
          count++;
        }
        assertEquals(howmany * howmany, count);
      } finally {
        reader.close();
      }
    }
  }

  /**
 * Tests that we can write out an edit, close, and then read it back in again.
 * @throws IOException
 */
public void testEditAdd() throws IOException {
	final int COL_COUNT = 10;
	final byte[] tableName = Bytes.toBytes("tablename");
	final byte[] row = Bytes.toBytes("row");
	HLog.Reader reader = null;
	HLog log = new HLog(fs, dir, this.oldLogDir, this.conf, null);
	try {
		long timestamp = System.currentTimeMillis();
		WALEdit cols = new WALEdit();
		for (int i = 0; i < COL_COUNT; i++) {
			cols.add(new KeyValue(row, Bytes.toBytes("column"), Bytes.toBytes(Integer.toString(i)), timestamp,
					new byte[] { (byte) (i + '0') }));
		}
		HRegionInfo info = new HRegionInfo(new HTableDescriptor(tableName), row,
				Bytes.toBytes(Bytes.toString(row) + "1"), false);
		final byte[] regionName = info.getRegionName();
		log.append(info, tableName, cols, System.currentTimeMillis());
		long logSeqId = log.startCacheFlush();
		log.completeCacheFlush(regionName, tableName, logSeqId, info.isMetaRegion());
		log.close();
		Path filename = log.computeFilename(log.getFilenum());
		log = null;
		reader = HLog.getReader(fs, filename, conf);
		for (int i = 0; i < 1; i++) {
			HLog.Entry entry = reader.next(null);
			if (entry == null)
				break;
			HLogKey key = entry.getKey();
			WALEdit val = entry.getEdit();
			assertTrue(Bytes.equals(regionName, key.getRegionName()));
			assertTrue(Bytes.equals(tableName, key.getTablename()));
			KeyValue kv = val.getKeyValues().get(0);
			assertTrue(Bytes.equals(row, kv.getRow()));
			assertEquals((byte) (i + '0'), kv.getValue()[0]);
			System.out.println(key + " " + val);
		}
		HLog.Entry entry = null;
		while ((entry = reader.next(null)) != null) {
			HLogKey key = entry.getKey();
			WALEdit val = entry.getEdit();
			assertTrue(Bytes.equals(regionName, key.getRegionName()));
			assertTrue(Bytes.equals(tableName, key.getTablename()));
			KeyValue kv = val.getKeyValues().get(0);
			assertTrue(Bytes.equals(HLog.METAROW, kv.getRow()));
			assertTrue(Bytes.equals(HLog.METAFAMILY, kv.getFamily()));
			assertEquals(0, Bytes.compareTo(HLog.COMPLETE_CACHE_FLUSH, val.getKeyValues().get(0).getValue()));
			System.out.println(key + " " + val);
		}
	} finally {
		if (log != null) {
			log.closeAndDelete();
		}
		if (reader != null) {
			reader.close();
		}
	}
}
}