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
package org.apache.hadoop.hbase.io.hfile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseTestCase;
import org.apache.hadoop.hbase.io.hfile.HFile.BlockIndex;
import org.apache.hadoop.hbase.io.hfile.HFile.Reader;
import org.apache.hadoop.hbase.io.hfile.HFile.Writer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.ClassSize;
import org.apache.hadoop.io.RawComparator;

/**
 * test hfile features.
 * <p>
 * Copied from
 * <a href="https://issues.apache.org/jira/browse/HADOOP-3315">hadoop-3315 tfile</a>.
 * Remove after tfile is committed and use the tfile version of this class
 * instead.</p>
 */
public class TestHFile extends HBaseTestCase {
  static final Log LOG = LogFactory.getLog(TestHFile.class);

  private static String ROOT_DIR =
    System.getProperty("test.build.data", "/tmp/TestHFile");
  private final int minBlockSize = 512;
  private static String localFormatter = "%010d";

  // write some records into the tfile
  // write them twice
  private int writeSomeRecords(Writer writer, int start, int n)
      throws IOException {
    String value = "value";
    for (int i = start; i < (start + n); i++) {
      String key = String.format(localFormatter, Integer.valueOf(i));
      writer.append(Bytes.toBytes(key), Bytes.toBytes(value + key));
    }
    return (start + n);
  }

  private void readAllRecords(HFileScanner scanner) throws IOException {
    readAndCheckbytes(scanner, 0, 100);
  }

  // read the records and check
  private int readAndCheckbytes(HFileScanner scanner, int start, int n)
      throws IOException {
    String value = "value";
    int i = start;
    for (; i < (start + n); i++) {
      ByteBuffer key = scanner.getKey();
      ByteBuffer val = scanner.getValue();
      String keyStr = String.format(localFormatter, Integer.valueOf(i));
      String valStr = value + keyStr;
      byte [] keyBytes = Bytes.toBytes(key);
      assertTrue("bytes for keys do not match " + keyStr + " " +
        Bytes.toString(Bytes.toBytes(key)),
          Arrays.equals(Bytes.toBytes(keyStr), keyBytes));
      byte [] valBytes = Bytes.toBytes(val);
      assertTrue("bytes for vals do not match " + valStr + " " +
        Bytes.toString(valBytes),
        Arrays.equals(Bytes.toBytes(valStr), valBytes));
      if (!scanner.next()) {
        break;
      }
    }
    assertEquals(i, start + n - 1);
    return (start + n);
  }

  private byte[] getSomeKey(int rowId) {
    return String.format(localFormatter, Integer.valueOf(rowId)).getBytes();
  }

  private void writeRecords(Writer writer) throws IOException {
    writeSomeRecords(writer, 0, 100);
    writer.close();
  }

  private FSDataOutputStream createFSOutput(Path name) throws IOException {
    if (fs.exists(name)) fs.delete(name, true);
    FSDataOutputStream fout = fs.create(name);
    return fout;
  }

  /**
   * test none codecs
   */
  void basicWithSomeCodec(String codec) throws IOException {
    Path ncTFile = new Path(ROOT_DIR, "basic.hfile");
    FSDataOutputStream fout = createFSOutput(ncTFile);
    Writer writer = new Writer(fout, minBlockSize,
      Compression.getCompressionAlgorithmByName(codec), null);
    LOG.info(writer);
    writeRecords(writer);
    fout.close();
    FSDataInputStream fin = fs.open(ncTFile);
    Reader reader = new Reader(fs.open(ncTFile),
      fs.getFileStatus(ncTFile).getLen(), null, false);
    // Load up the index.
    reader.loadFileInfo();
    // Get a scanner that caches and that does not use pread.
    HFileScanner scanner = reader.getScanner(true, false);
    // Align scanner at start of the file.
    scanner.seekTo();
    readAllRecords(scanner);
    scanner.seekTo(getSomeKey(50));
    assertTrue("location lookup failed", scanner.seekTo(getSomeKey(50)) == 0);
    // read the key and see if it matches
    ByteBuffer readKey = scanner.getKey();
    assertTrue("seeked key does not match", Arrays.equals(getSomeKey(50),
      Bytes.toBytes(readKey)));

    scanner.seekTo(new byte[0]);
    ByteBuffer val1 = scanner.getValue();
    scanner.seekTo(new byte[0]);
    ByteBuffer val2 = scanner.getValue();
    assertTrue(Arrays.equals(Bytes.toBytes(val1), Bytes.toBytes(val2)));

    reader.close();
    fin.close();
    fs.delete(ncTFile, true);
  }

  private void writeNumMetablocks(Writer writer, int n) {
    for (int i = 0; i < n; i++) {
      writer.appendMetaBlock("HFileMeta" + i, ("something to test" + i).getBytes());
    }
  }

  private void someTestingWithMetaBlock(Writer writer) {
    writeNumMetablocks(writer, 10);
  }

  private void readNumMetablocks(Reader reader, int n) throws IOException {
    for (int i = 0; i < n; i++) {
      ByteBuffer b = reader.getMetaBlock("HFileMeta" + i);
      byte [] found = Bytes.toBytes(b);
      assertTrue("failed to match metadata", Arrays.equals(
          ("something to test" + i).getBytes(), found));
    }
  }

  private void someReadingWithMetaBlock(Reader reader) throws IOException {
    readNumMetablocks(reader, 10);
  }

  private void metablocks(final String compress) throws Exception {
    Path mFile = new Path(ROOT_DIR, "meta.hfile");
    FSDataOutputStream fout = createFSOutput(mFile);
    Writer writer = new Writer(fout, minBlockSize,
      Compression.getCompressionAlgorithmByName(compress), null);
    someTestingWithMetaBlock(writer);
    writer.close();
    fout.close();
    FSDataInputStream fin = fs.open(mFile);
    Reader reader = new Reader(fs.open(mFile), this.fs.getFileStatus(mFile)
        .getLen(), null, false);
    reader.loadFileInfo();
    // No data -- this should return false.
    assertFalse(reader.getScanner(false, false).seekTo());
    someReadingWithMetaBlock(reader);
    fs.delete(mFile, true);
    reader.close();
    fin.close();
  }

  /**
 * Make sure the orginals for our compression libs doesn't change on us.
 */
public void testCompressionOrdinance() {
	assertTrue(Compression.Algorithm.GZ.ordinal() == 1);
	assertTrue(Compression.Algorithm.NONE.ordinal() == 2);
}

}
