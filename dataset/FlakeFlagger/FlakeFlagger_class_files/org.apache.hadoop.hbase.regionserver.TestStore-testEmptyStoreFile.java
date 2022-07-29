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
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.io.hfile.HFile.Writer;
import org.apache.hadoop.hbase.regionserver.wal.HLog;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.util.Progressable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Test class fosr the Store
 */
public class TestStore extends TestCase {
  Store store;
  byte [] table = Bytes.toBytes("table");
  byte [] family = Bytes.toBytes("family");

  byte [] row = Bytes.toBytes("row");
  byte [] qf1 = Bytes.toBytes("qf1");
  byte [] qf2 = Bytes.toBytes("qf2");
  byte [] qf3 = Bytes.toBytes("qf3");
  byte [] qf4 = Bytes.toBytes("qf4");
  byte [] qf5 = Bytes.toBytes("qf5");
  byte [] qf6 = Bytes.toBytes("qf6");

  NavigableSet<byte[]> qualifiers =
    new ConcurrentSkipListSet<byte[]>(Bytes.BYTES_COMPARATOR);

  List<KeyValue> expected = new ArrayList<KeyValue>();
  List<KeyValue> result = new ArrayList<KeyValue>();

  long id = System.currentTimeMillis();
  Get get = new Get(row);

  private static final String DIR = "test/build/data/TestStore/";

  private void init(String methodName) throws IOException {
    //Setting up a Store
    Path basedir = new Path(DIR+methodName);
    Path logdir = new Path(DIR+methodName+"/logs");
    Path oldLogDir = new Path(basedir, HConstants.HREGION_OLDLOGDIR_NAME);
    HColumnDescriptor hcd = new HColumnDescriptor(family);
    HBaseConfiguration conf = new HBaseConfiguration();
    FileSystem fs = FileSystem.get(conf);
    Path reconstructionLog = null;
    Progressable reporter = null;

    fs.delete(logdir, true);

    HTableDescriptor htd = new HTableDescriptor(table);
    htd.addFamily(hcd);
    HRegionInfo info = new HRegionInfo(htd, null, null, false);
    HLog hlog = new HLog(fs, logdir, oldLogDir, conf, null);
    HRegion region = new HRegion(basedir, hlog, fs, conf, info, null);

    store = new Store(basedir, region, hcd, fs, reconstructionLog, conf,
        reporter);
  }


  //////////////////////////////////////////////////////////////////////////////
  // Get tests
  //////////////////////////////////////////////////////////////////////////////

  /**
 * Test for hbase-1686.
 * @throws IOException
 */
public void testEmptyStoreFile() throws IOException {
	init(this.getName());
	this.store.add(new KeyValue(row, family, qf1, null));
	this.store.add(new KeyValue(row, family, qf2, null));
	flush(1);
	StoreFile f = this.store.getStorefiles().firstEntry().getValue();
	Path storedir = f.getPath().getParent();
	long seqid = f.getMaxSequenceId();
	HBaseConfiguration c = new HBaseConfiguration();
	FileSystem fs = FileSystem.get(c);
	Writer w = StoreFile.getWriter(fs, storedir);
	StoreFile.appendMetadata(w, seqid + 1);
	w.close();
	this.store.close();
	this.store = new Store(storedir.getParent().getParent(), this.store.getHRegion(), this.store.getFamily(), fs, null,
			c, null);
	System.out.println(this.store.getHRegionInfo().getEncodedName());
	assertEquals(2, this.store.getStorefilesCount());
	this.store.get(get, qualifiers, result);
	assertEquals(1, result.size());
}

  private void flush(int storeFilessize) throws IOException{
    this.store.snapshot();
    flushStore(store, id++);
    assertEquals(storeFilessize, this.store.getStorefiles().size());
    assertEquals(0, this.store.memstore.kvset.size());
  }

  private void assertCheck() {
    assertEquals(expected.size(), result.size());
    for(int i=0; i<expected.size(); i++) {
      assertEquals(expected.get(i), result.get(i));
    }
  }

  private static void flushStore(Store store, long id) throws IOException {
    StoreFlusher storeFlusher = store.getStoreFlusher(id);
    storeFlusher.prepare();
    storeFlusher.flushCache();
    storeFlusher.commit();
  }
}