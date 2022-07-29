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

  /**
 * Test file of multiple deletes and with deletes as final key.
 * @see  <a href="https://issues.apache.org/jira/browse/HBASE-751">HBASE-751</a>
 */
public void testGetClosestRowBefore3() throws IOException {
	HRegion region = null;
	byte[] c0 = COLUMNS[0];
	byte[] c1 = COLUMNS[1];
	try {
		HTableDescriptor htd = createTableDescriptor(getName());
		region = createNewHRegion(htd, null, null);
		Put p = new Put(T00);
		p.add(c0, c0, T00);
		region.put(p);
		p = new Put(T10);
		p.add(c0, c0, T10);
		region.put(p);
		p = new Put(T20);
		p.add(c0, c0, T20);
		region.put(p);
		Result r = region.getClosestRowBefore(T20, c0);
		assertTrue(Bytes.equals(T20, r.getRow()));
		Delete d = new Delete(T20);
		d.deleteColumn(c0, c0);
		region.delete(d, null, false);
		r = region.getClosestRowBefore(T20, c0);
		assertTrue(Bytes.equals(T10, r.getRow()));
		p = new Put(T30);
		p.add(c0, c0, T30);
		region.put(p);
		r = region.getClosestRowBefore(T30, c0);
		assertTrue(Bytes.equals(T30, r.getRow()));
		d = new Delete(T30);
		d.deleteColumn(c0, c0);
		region.delete(d, null, false);
		r = region.getClosestRowBefore(T30, c0);
		assertTrue(Bytes.equals(T10, r.getRow()));
		r = region.getClosestRowBefore(T31, c0);
		assertTrue(Bytes.equals(T10, r.getRow()));
		region.flushcache();
		r = region.getClosestRowBefore(T30, c0);
		assertTrue(Bytes.equals(T10, r.getRow()));
		r = region.getClosestRowBefore(T31, c0);
		assertTrue(Bytes.equals(T10, r.getRow()));
		p = new Put(T20);
		p.add(c1, c1, T20);
		region.put(p);
		r = region.getClosestRowBefore(T30, c0);
		assertTrue(Bytes.equals(T10, r.getRow()));
		r = region.getClosestRowBefore(T31, c0);
		assertTrue(Bytes.equals(T10, r.getRow()));
		region.flushcache();
		r = region.getClosestRowBefore(T30, c0);
		assertTrue(Bytes.equals(T10, r.getRow()));
		r = region.getClosestRowBefore(T31, c0);
		assertTrue(Bytes.equals(T10, r.getRow()));
		d = new Delete(T20);
		d.deleteColumn(c1, c1);
		region.delete(d, null, false);
		r = region.getClosestRowBefore(T30, c0);
		assertTrue(Bytes.equals(T10, r.getRow()));
		r = region.getClosestRowBefore(T31, c0);
		assertTrue(Bytes.equals(T10, r.getRow()));
		region.flushcache();
		r = region.getClosestRowBefore(T31, c0);
		assertTrue(Bytes.equals(T10, r.getRow()));
		p = new Put(T11);
		p.add(c0, c0, T11);
		region.put(p);
		d = new Delete(T10);
		d.deleteColumn(c1, c1);
		r = region.getClosestRowBefore(T12, c0);
		assertTrue(Bytes.equals(T11, r.getRow()));
	} finally {
		if (region != null) {
			try {
				region.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			region.getLog().closeAndDelete();
		}
	}
}
}