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
 * @throws IOException
 */public void testAppend() throws IOException{final int COL_COUNT=10;final byte[] tableName=Bytes.toBytes("tablename");final byte[] row=Bytes.toBytes("row");this.conf.setBoolean("dfs.support.append",true);Reader reader=null;HLog log=new HLog(this.fs,dir,this.oldLogDir,this.conf,null);try {long timestamp=System.currentTimeMillis();WALEdit cols=new WALEdit();for (int i=0;i < COL_COUNT;i++){cols.add(new KeyValue(row,Bytes.toBytes("column"),Bytes.toBytes(Integer.toString(i)),timestamp,new byte[]{(byte)(i + '0')}));}HRegionInfo hri=new HRegionInfo(new HTableDescriptor(tableName),HConstants.EMPTY_START_ROW,HConstants.EMPTY_END_ROW);log.append(hri,tableName,cols,System.currentTimeMillis());long logSeqId=log.startCacheFlush();log.completeCacheFlush(hri.getRegionName(),tableName,logSeqId,false);log.close();Path filename=log.computeFilename(log.getFilenum());log=null;reader=HLog.getReader(fs,filename,conf);HLog.Entry entry=reader.next();assertEquals(COL_COUNT,entry.getEdit().size());int idx=0;for (KeyValue val:entry.getEdit().getKeyValues()){assertTrue(Bytes.equals(hri.getRegionName(),entry.getKey().getRegionName()));assertTrue(Bytes.equals(tableName,entry.getKey().getTablename()));assertTrue(Bytes.equals(row,val.getRow()));assertEquals((byte)(idx + '0'),val.getValue()[0]);System.out.println(entry.getKey() + " " + val);idx++;}entry=reader.next();assertEquals(1,entry.getEdit().size());for (KeyValue val:entry.getEdit().getKeyValues()){assertTrue(Bytes.equals(hri.getRegionName(),entry.getKey().getRegionName()));assertTrue(Bytes.equals(tableName,entry.getKey().getTablename()));assertTrue(Bytes.equals(HLog.METAROW,val.getRow()));assertTrue(Bytes.equals(HLog.METAFAMILY,val.getFamily()));assertEquals(0,Bytes.compareTo(HLog.COMPLETE_CACHE_FLUSH,val.getValue()));System.out.println(entry.getKey() + " " + val);}}  finally {if (log != null){log.closeAndDelete();}if (reader != null){reader.close();}}}
}