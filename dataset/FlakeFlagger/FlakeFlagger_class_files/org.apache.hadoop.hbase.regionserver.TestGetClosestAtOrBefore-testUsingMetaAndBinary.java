/**
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseTestCase;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.Writables;
import org.apache.hadoop.hdfs.MiniDFSCluster;

/**
 * {@link TestGet} is a medley of tests of get all done up as a single test.
 * This class
 */
public class TestGetClosestAtOrBefore extends HBaseTestCase implements HConstants {
  static final Log LOG = LogFactory.getLog(TestGetClosestAtOrBefore.class);
  private MiniDFSCluster miniHdfs;

  private static final byte [] T00 = Bytes.toBytes("000");
  private static final byte [] T10 = Bytes.toBytes("010");
  private static final byte [] T11 = Bytes.toBytes("011");
  private static final byte [] T12 = Bytes.toBytes("012");
  private static final byte [] T20 = Bytes.toBytes("020");
  private static final byte [] T30 = Bytes.toBytes("030");
  private static final byte [] T31 = Bytes.toBytes("031");
  private static final byte [] T35 = Bytes.toBytes("035");
  private static final byte [] T40 = Bytes.toBytes("040");

  public void testUsingMetaAndBinary() throws IOException{FileSystem filesystem=FileSystem.get(conf);Path rootdir=filesystem.makeQualified(new Path(conf.get(HConstants.HBASE_DIR)));filesystem.mkdirs(rootdir);HRegionInfo.FIRST_META_REGIONINFO.getTableDesc().setMemStoreFlushSize(64 * 1024 * 1024);HRegion mr=HRegion.createHRegion(HRegionInfo.FIRST_META_REGIONINFO,rootdir,this.conf);for (char c='A';c < 'D';c++){HTableDescriptor htd=new HTableDescriptor("" + c);final int last=128;final int interval=2;for (int i=0;i <= last;i+=interval){HRegionInfo hri=new HRegionInfo(htd,i == 0?HConstants.EMPTY_BYTE_ARRAY:Bytes.toBytes((byte)i),i == last?HConstants.EMPTY_BYTE_ARRAY:Bytes.toBytes((byte)i + interval));Put put=new Put(hri.getRegionName());put.add(CATALOG_FAMILY,REGIONINFO_QUALIFIER,Writables.getBytes(hri));mr.put(put,false);}}InternalScanner s=mr.getScanner(new Scan());try {List<KeyValue> keys=new ArrayList<KeyValue>();while (s.next(keys)){LOG.info(keys);keys.clear();}}  finally {s.close();}findRow(mr,'C',44,44);findRow(mr,'C',45,44);findRow(mr,'C',46,46);findRow(mr,'C',43,42);mr.flushcache();findRow(mr,'C',44,44);findRow(mr,'C',45,44);findRow(mr,'C',46,46);findRow(mr,'C',43,42);byte[] firstRowInC=HRegionInfo.createRegionName(Bytes.toBytes("" + 'C'),HConstants.EMPTY_BYTE_ARRAY,HConstants.ZEROES);Scan scan=new Scan(firstRowInC);s=mr.getScanner(scan);try {List<KeyValue> keys=new ArrayList<KeyValue>();while (s.next(keys)){mr.delete(new Delete(keys.get(0).getRow()),null,false);keys.clear();}}  finally {s.close();}findRow(mr,'C',44,-1);findRow(mr,'C',45,-1);findRow(mr,'C',46,-1);findRow(mr,'C',43,-1);mr.flushcache();findRow(mr,'C',44,-1);findRow(mr,'C',45,-1);findRow(mr,'C',46,-1);findRow(mr,'C',43,-1);}

  /*
   * @param mr
   * @param table
   * @param rowToFind
   * @param answer Pass -1 if we're not to find anything.
   * @return Row found.
   * @throws IOException
   */
  private byte [] findRow(final HRegion mr, final char table,
    final int rowToFind, final int answer)
  throws IOException {
    byte [] tableb = Bytes.toBytes("" + table);
    // Find the row.
    byte [] tofindBytes = Bytes.toBytes((short)rowToFind);
    byte [] metaKey = HRegionInfo.createRegionName(tableb, tofindBytes,
      HConstants.NINES);
    LOG.info("find=" + new String(metaKey));
    Result r = mr.getClosestRowBefore(metaKey);
    if (answer == -1) {
      assertNull(r);
      return null;
    }
    assertTrue(Bytes.compareTo(Bytes.toBytes((short)answer),
      extractRowFromMetaRow(r.getRow())) == 0);
    return r.getRow();
  }

  private byte [] extractRowFromMetaRow(final byte [] b) {
    int firstDelimiter = KeyValue.getDelimiter(b, 0, b.length,
      HRegionInfo.DELIMITER);
    int lastDelimiter = KeyValue.getDelimiterInReverse(b, 0, b.length,
      HRegionInfo.DELIMITER);
    int length = lastDelimiter - firstDelimiter - 1;
    byte [] row = new byte[length];
    System.arraycopy(b, firstDelimiter + 1, row, 0, length);
    return row;
  }
}