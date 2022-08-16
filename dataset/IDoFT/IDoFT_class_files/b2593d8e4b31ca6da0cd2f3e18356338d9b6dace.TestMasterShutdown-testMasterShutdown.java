/**
 * Copyright The Apache Software Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.alibaba.wasp.master;

import com.alibaba.wasp.ClusterStatus;
import com.alibaba.wasp.MiniWaspCluster;
import com.alibaba.wasp.WaspTestingUtility;
import com.alibaba.wasp.conf.WaspConfiguration;
import com.alibaba.wasp.util.JVMClusterUtil.MasterThread;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestMasterShutdown {
  private static final Log LOG = LogFactory.getLog(TestMasterShutdown.class);

  private Configuration conf = WaspConfiguration.create();

  /**
   * Simple test of shutdown.
   * <p>
   * Starts with three masters. Tells the active master to shutdown the cluster.
   * Verifies that all masters are properly shutdown.
   * @throws Exception
   */
  @Test(timeout = 240000)
  public void testMasterShutdown() throws Exception {

    final int NUM_MASTERS = 3;
    final int NUM_RS = 3;

    // Start the cluster
    WaspTestingUtility TEST_UTIL = new WaspTestingUtility(conf);
    TEST_UTIL.startMiniCluster(NUM_MASTERS, NUM_RS);
    MiniWaspCluster cluster = TEST_UTIL.getWaspCluster();

    // get all the master threads
    List<MasterThread> masterThreads = cluster.getMasterThreads();

    // wait for each to come online
    for (MasterThread mt : masterThreads) {
      assertTrue(mt.isAlive());
    }

    // find the active master
    FMaster active = null;
    for (int i = 0; i < masterThreads.size(); i++) {
      if (masterThreads.get(i).getMaster().isActiveMaster()) {
        active = masterThreads.get(i).getMaster();
        break;
      }
    }
    assertNotNull(active);
    // make sure the other two are backup masters
    ClusterStatus status = active.getClusterStatus();
    assertEquals(2, status.getBackupMastersSize());
    assertEquals(2, status.getBackupMasters().size());

    // tell the active master to shutdown the cluster
    active.shutdown();

    for (int i = NUM_MASTERS - 1; i >= 0; --i) {
      cluster.waitOnMaster(i);
    }
    // make sure all the masters properly shutdown
    assertEquals(0, masterThreads.size());

    TEST_UTIL.shutdownMiniCluster();
  }
}
