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
package org.apache.hadoop.hbase;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;

import org.apache.hadoop.hbase.regionserver.HRegionServer;
import org.apache.hadoop.hbase.regionserver.HRegion;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.JVMClusterUtil;

/**
 * Test whether region rebalancing works. (HBASE-71)
 */
public class TestRegionRebalancing extends HBaseClusterTestCase {
  final Log LOG = LogFactory.getLog(this.getClass().getName());
  HTable table;

  HTableDescriptor desc;

  final byte[] FIVE_HUNDRED_KBYTES;

  final byte [] FAMILY_NAME = Bytes.toBytes("col");

  /**
 * For HBASE-71. Try a few different configurations of starting and stopping region servers to see if the assignment or regions is pretty balanced.
 * @throws IOException
 */
public void testRebalancing() throws IOException {
	table = new HTable(conf, "test");
	assertEquals("Test table should have 20 regions", 20, table.getStartKeys().length);
	assertRegionsAreBalanced();
	LOG.debug("Adding 2nd region server.");
	cluster.startRegionServer();
	assertRegionsAreBalanced();
	LOG.debug("Adding 3rd region server.");
	cluster.startRegionServer();
	assertRegionsAreBalanced();
	LOG.debug("Killing the 3rd region server.");
	cluster.stopRegionServer(2, false);
	cluster.waitOnRegionServer(2);
	assertRegionsAreBalanced();
	LOG.debug("Adding 3rd region server");
	cluster.startRegionServer();
	LOG.debug("Adding 4th region server");
	cluster.startRegionServer();
	assertRegionsAreBalanced();
	for (int i = 0; i < 6; i++) {
		LOG.debug("Adding " + (i + 5) + "th region server");
		cluster.startRegionServer();
	}
	assertRegionsAreBalanced();
}

  /** figure out how many regions are currently being served. */
  private int getRegionCount() {
    int total = 0;
    for (HRegionServer server : getOnlineRegionServers()) {
      total += server.getOnlineRegions().size();
    }
    return total;
  }

  /**
   * Determine if regions are balanced. Figure out the total, divide by the
   * number of online servers, then test if each server is +/- 1 of average
   * rounded up.
   */
  private void assertRegionsAreBalanced() {
    boolean success = false;
    float slop = conf.getFloat("hbase.regions.slop", (float)0.1);
    if (slop <= 0) slop = 1;

    for (int i = 0; i < 5; i++) {
      success = true;
      // make sure all the regions are reassigned before we test balance
      waitForAllRegionsAssigned();

      int regionCount = getRegionCount();
      List<HRegionServer> servers = getOnlineRegionServers();
      double avg = cluster.getMaster().getAverageLoad();
      int avgLoadPlusSlop = (int)Math.ceil(avg * (1 + slop));
      int avgLoadMinusSlop = (int)Math.floor(avg * (1 - slop)) - 1;
      LOG.debug("There are " + servers.size() + " servers and " + regionCount
        + " regions. Load Average: " + avg + " low border: " + avgLoadMinusSlop
        + ", up border: " + avgLoadPlusSlop + "; attempt: " + i);

      for (HRegionServer server : servers) {
        int serverLoad = server.getOnlineRegions().size();
        LOG.debug(server.hashCode() + " Avg: " + avg + " actual: " + serverLoad);
        if (!(avg > 2.0 && serverLoad <= avgLoadPlusSlop
            && serverLoad >= avgLoadMinusSlop)) {
          LOG.debug(server.hashCode() + " Isn't balanced!!! Avg: " + avg +
              " actual: " + serverLoad + " slop: " + slop);
          success = false;
        }
      }

      if (!success) {
        // one or more servers are not balanced. sleep a little to give it a
        // chance to catch up. then, go back to the retry loop.
        try {
          Thread.sleep(10000);
        } catch (InterruptedException e) {}

        continue;
      }

      // if we get here, all servers were balanced, so we should just return.
      return;
    }
    // if we get here, we tried 5 times and never got to short circuit out of
    // the retry loop, so this is a failure.
    fail("After 5 attempts, region assignments were not balanced.");
  }

  private List<HRegionServer> getOnlineRegionServers() {
    List<HRegionServer> list = new ArrayList<HRegionServer>();
    for (JVMClusterUtil.RegionServerThread rst : cluster.getRegionServerThreads()) {
      if (rst.getRegionServer().isOnline()) {
        list.add(rst.getRegionServer());
      }
    }
    return list;
  }

  /**
   * Wait until all the regions are assigned.
   */
  private void waitForAllRegionsAssigned() {
    while (getRegionCount() < 22) {
    // while (!cluster.getMaster().allRegionsAssigned()) {
      LOG.debug("Waiting for there to be 22 regions, but there are " + getRegionCount() + " right now.");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {}
    }
  }

  /**
   * create a region with the specified start and end key and exactly one row
   * inside.
   */
  private HRegion createAregion(byte [] startKey, byte [] endKey)
  throws IOException {
    HRegion region = createNewHRegion(desc, startKey, endKey);
    byte [] keyToWrite = startKey == null ? Bytes.toBytes("row_000") : startKey;
    Put put = new Put(keyToWrite);
    put.add(FAMILY_NAME, null, Bytes.toBytes("test"));
    region.put(put);
    region.close();
    region.getLog().closeAndDelete();
    return region;
  }
}