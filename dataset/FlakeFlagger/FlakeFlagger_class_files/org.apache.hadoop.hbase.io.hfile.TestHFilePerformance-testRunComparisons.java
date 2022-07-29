/**
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

import junit.framework.TestCase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.GzipCodec;

/**
 *  Set of long-running tests to measure performance of HFile.
 * <p>
 * Copied from
 * <a href="https://issues.apache.org/jira/browse/HADOOP-3315">hadoop-3315 tfile</a>.
 * Remove after tfile is committed and use the tfile version of this class
 * instead.</p>
 */
public class TestHFilePerformance extends TestCase {
  private static String ROOT_DIR =
      System.getProperty("test.build.data", "/tmp/TestHFilePerformance");
  private FileSystem fs;
  private Configuration conf;
  private long startTimeEpoch;
  private long finishTimeEpoch;
  private DateFormat formatter;

  private FSDataOutputStream createFSOutput(Path name) throws IOException {
    if (fs.exists(name))
      fs.delete(name, true);
    FSDataOutputStream fout = fs.create(name);
    return fout;
  }

  //TODO have multiple ways of generating key/value e.g. dictionary words
  //TODO to have a sample compressable data, for now, made 1 out of 3 values random
  //     keys are all random.

  private static class KeyValueGenerator {
    Random keyRandomizer;
    Random valueRandomizer;
    long randomValueRatio = 3; // 1 out of randomValueRatio generated values will be random.
    long valueSequence = 0 ;


    KeyValueGenerator() {
      keyRandomizer = new Random(0L); //TODO with seed zero
      valueRandomizer = new Random(1L); //TODO with seed one
    }

    // Key is always random now.
    void getKey(byte[] key) {
      keyRandomizer.nextBytes(key);
    }

    void getValue(byte[] value) {
      if (valueSequence % randomValueRatio == 0)
          valueRandomizer.nextBytes(value);
      valueSequence++;
    }
  }

  public void testRunComparisons() throws IOException {
	int keyLength = 100;
	int valueLength = 5 * 1024;
	int minBlockSize = 10 * 1024 * 1024;
	int rows = 10000;
	System.out.println("****************************** Sequence File *****************************");
	timeWrite("SequenceFile", keyLength, valueLength, "none", rows, null, minBlockSize);
	System.out.println("\n+++++++\n");
	timeReading("SequenceFile", keyLength, valueLength, rows, -1);
	System.out.println("");
	System.out.println("----------------------");
	System.out.println("");
	try {
		timeWrite("SequenceFile", keyLength, valueLength, "gz", rows, null, minBlockSize);
		System.out.println("\n+++++++\n");
		timeReading("SequenceFile", keyLength, valueLength, rows, -1);
	} catch (IllegalArgumentException e) {
		System.out.println("Skipping sequencefile gz: " + e.getMessage());
	}
	System.out.println("\n\n\n");
	System.out.println("****************************** HFile *****************************");
	timeWrite("HFile", keyLength, valueLength, "none", rows, null, minBlockSize);
	System.out.println("\n+++++++\n");
	timeReading("HFile", keyLength, valueLength, rows, 0);
	System.out.println("");
	System.out.println("----------------------");
	System.out.println("");
	timeWrite("HFile", keyLength, valueLength, "gz", rows, null, minBlockSize);
	System.out.println("\n+++++++\n");
	timeReading("HFile", keyLength, valueLength, rows, 0);
	System.out.println("\n\n\n\nNotes: ");
	System.out.println(" * Timing includes open/closing of files.");
	System.out.println(" * Timing includes reading both Key and Value");
	System.out.println(" * Data is generated as random bytes. Other methods e.g. using "
			+ "dictionary with care for distributation of words is under development.");
	System.out.println(" * Timing of write currently, includes random value/key generations. "
			+ "Which is the same for Sequence File and HFile. Another possibility is to generate "
			+ "test data beforehand");
	System.out.println(" * We need to mitigate cache effect on benchmark. We can apply several "
			+ "ideas, for next step we do a large dummy read between benchmark read to dismantle "
			+ "caching of data. Renaming of file may be helpful. We can have a loop that reads with"
			+ " the same method several times and flood cache every time and average it to get a" + " better number.");
}
}
