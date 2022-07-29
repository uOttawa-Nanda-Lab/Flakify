/**
 * Copyright 2008 The Apache Software Foundation
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

package org.apache.hadoop.hbase.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseTestCase;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.regionserver.wal.HLog;
import org.apache.hadoop.hbase.regionserver.HRegion;
import org.apache.hadoop.hbase.regionserver.InternalScanner;
import org.apache.hadoop.hdfs.MiniDFSCluster;
import org.apache.hadoop.util.ToolRunner;

/** Test stand alone merge tool that can merge arbitrary regions */
public class TestMergeTool extends HBaseTestCase {
  static final Log LOG = LogFactory.getLog(TestMergeTool.class);
//  static final byte [] COLUMN_NAME = Bytes.toBytes("contents:");
  static final byte [] FAMILY = Bytes.toBytes("contents");
  static final byte [] QUALIFIER = Bytes.toBytes("dc");

  private final HRegionInfo[] sourceRegions = new HRegionInfo[5];
  private final HRegion[] regions = new HRegion[5];
  private HTableDescriptor desc;
  private byte [][][] rows;
  private MiniDFSCluster dfsCluster = null;

  /*
   * @param msg Message that describes this merge
   * @param regionName1
   * @param regionName2
   * @param log Log to use merging.
   * @param upperbound Verifying, how high up in this.rows to go.
   * @return Merged region.
   * @throws Exception
   */
  private HRegion mergeAndVerify(final String msg, final String regionName1,
    final String regionName2, final HLog log, final int upperbound)
  throws Exception {
    Merge merger = new Merge(this.conf);
    LOG.info(msg);
    System.out.println("fs2=" + this.conf.get("fs.defaultFS"));
    int errCode = ToolRunner.run(this.conf, merger,
      new String[] {this.desc.getNameAsString(), regionName1, regionName2}
    );
    assertTrue("'" + msg + "' failed", errCode == 0);
    HRegionInfo mergedInfo = merger.getMergedHRegionInfo();

    // Now verify that we can read all the rows from regions 0, 1
    // in the new merged region.
    HRegion merged =
      HRegion.openHRegion(mergedInfo, this.testDir, log, this.conf);
    verifyMerge(merged, upperbound);
    merged.close();
    LOG.info("Verified " + msg);
    return merged;
  }

  private void verifyMerge(final HRegion merged, final int upperbound)
  throws IOException {
    //Test
    Scan scan = new Scan();
    scan.addFamily(FAMILY);
    InternalScanner scanner = merged.getScanner(scan);
    try {
    List<KeyValue> testRes = null;
      while (true) {
        testRes = new ArrayList<KeyValue>();
        boolean hasNext = scanner.next(testRes);
        if (!hasNext) {
          break;
        }
      }
    } finally {
      scanner.close();
    }

    //!Test

    for (int i = 0; i < upperbound; i++) {
      for (int j = 0; j < rows[i].length; j++) {
        Get get = new Get(rows[i][j]);
        get.addFamily(FAMILY);
        Result result = merged.get(get, null);
        assertEquals(1, result.size());
        byte [] bytes = result.sorted()[0].getValue();
        assertNotNull(Bytes.toStringBinary(rows[i][j]), bytes);
        assertTrue(Bytes.equals(bytes, rows[i][j]));
      }
    }
  }

  /**
 * Test merge tool.
 * @throws Exception
 */
public void testMergeTool() throws Exception {
	for (int i = 0; i < regions.length; i++) {
		for (int j = 0; j < rows[i].length; j++) {
			Get get = new Get(rows[i][j]);
			get.addFamily(FAMILY);
			Result result = regions[i].get(get, null);
			byte[] bytes = result.sorted()[0].getValue();
			assertNotNull(bytes);
			assertTrue(Bytes.equals(bytes, rows[i][j]));
		}
		regions[i].close();
		regions[i].getLog().closeAndDelete();
	}
	Path logPath = new Path("/tmp", HConstants.HREGION_LOGDIR_NAME + "_" + System.currentTimeMillis());
	LOG.info("Creating log " + logPath.toString());
	Path oldLogDir = new Path("/tmp", HConstants.HREGION_OLDLOGDIR_NAME);
	HLog log = new HLog(this.fs, logPath, oldLogDir, this.conf, null);
	try {
		HRegion merged = mergeAndVerify("merging regions 0 and 1", this.sourceRegions[0].getRegionNameAsString(),
				this.sourceRegions[1].getRegionNameAsString(), log, 2);
		merged = mergeAndVerify("merging regions 0+1 and 2", merged.getRegionInfo().getRegionNameAsString(),
				this.sourceRegions[2].getRegionNameAsString(), log, 3);
		merged = mergeAndVerify("merging regions 0+1+2 and 3", merged.getRegionInfo().getRegionNameAsString(),
				this.sourceRegions[3].getRegionNameAsString(), log, 4);
		merged = mergeAndVerify("merging regions 0+1+2+3 and 4", merged.getRegionInfo().getRegionNameAsString(),
				this.sourceRegions[4].getRegionNameAsString(), log, rows.length);
	} finally {
		log.closeAndDelete();
	}
}
}
