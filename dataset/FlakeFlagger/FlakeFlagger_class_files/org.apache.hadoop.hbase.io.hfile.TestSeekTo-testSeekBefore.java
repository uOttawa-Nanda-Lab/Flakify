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

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseTestCase;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Test {@link HFileScanner#seekTo(byte[])} and its variants.
 */
public class TestSeekTo extends HBaseTestCase {

  Path makeNewFile() throws IOException {
    Path ncTFile = new Path(this.testDir, "basic.hfile");
    FSDataOutputStream fout = this.fs.create(ncTFile);
    HFile.Writer writer = new HFile.Writer(fout, 40, "none", null);
    // 4 bytes * 3 * 2 for each key/value +
    // 3 for keys, 15 for values = 42 (woot)
    writer.append(Bytes.toBytes("c"), Bytes.toBytes("value"));
    writer.append(Bytes.toBytes("e"), Bytes.toBytes("value"));
    writer.append(Bytes.toBytes("g"), Bytes.toBytes("value"));
    // block transition
    writer.append(Bytes.toBytes("i"), Bytes.toBytes("value"));
    writer.append(Bytes.toBytes("k"), Bytes.toBytes("value"));
    writer.close();
    fout.close();
    return ncTFile;
  }
  public void testSeekBefore() throws Exception {
	Path p = makeNewFile();
	HFile.Reader reader = new HFile.Reader(fs, p, null, false);
	reader.loadFileInfo();
	HFileScanner scanner = reader.getScanner(false, true);
	assertEquals(false, scanner.seekBefore(Bytes.toBytes("a")));
	assertEquals(false, scanner.seekBefore(Bytes.toBytes("c")));
	assertEquals(true, scanner.seekBefore(Bytes.toBytes("d")));
	assertEquals("c", scanner.getKeyString());
	assertEquals(true, scanner.seekBefore(Bytes.toBytes("e")));
	assertEquals("c", scanner.getKeyString());
	assertEquals(true, scanner.seekBefore(Bytes.toBytes("f")));
	assertEquals("e", scanner.getKeyString());
	assertEquals(true, scanner.seekBefore(Bytes.toBytes("g")));
	assertEquals("e", scanner.getKeyString());
	assertEquals(true, scanner.seekBefore(Bytes.toBytes("h")));
	assertEquals("g", scanner.getKeyString());
	assertEquals(true, scanner.seekBefore(Bytes.toBytes("i")));
	assertEquals("g", scanner.getKeyString());
	assertEquals(true, scanner.seekBefore(Bytes.toBytes("j")));
	assertEquals("i", scanner.getKeyString());
	assertEquals(true, scanner.seekBefore(Bytes.toBytes("k")));
	assertEquals("i", scanner.getKeyString());
	assertEquals(true, scanner.seekBefore(Bytes.toBytes("l")));
	assertEquals("k", scanner.getKeyString());
}
}