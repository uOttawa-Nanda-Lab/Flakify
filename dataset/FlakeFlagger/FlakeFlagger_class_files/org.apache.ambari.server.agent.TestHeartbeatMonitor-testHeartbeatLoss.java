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
package org.apache.ambari.server.agent;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.apache.ambari.server.AmbariException;
import org.apache.ambari.server.Role;
import org.apache.ambari.server.actionmanager.ActionManager;
import org.apache.ambari.server.api.services.AmbariMetaInfo;
import org.apache.ambari.server.orm.GuiceJpaInitializer;
import org.apache.ambari.server.orm.InMemoryDefaultTestModule;
import org.apache.ambari.server.state.Cluster;
import org.apache.ambari.server.state.Clusters;
import org.apache.ambari.server.state.HostState;
import org.apache.ambari.server.state.Service;
import org.apache.ambari.server.state.StackId;
import org.apache.ambari.server.state.cluster.ClustersImpl;
import org.apache.ambari.server.state.fsm.InvalidStateTransitionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import org.mockito.ArgumentCaptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestHeartbeatMonitor {

  private static Injector injector;

  private String hostname1 = "host1";
  private String hostname2 = "host2";
  private String clusterName = "cluster1";
  private String serviceName = "HDFS";
  private int heartbeatMonitorWakeupIntervalMS = 30;
  private AmbariMetaInfo ambariMetaInfo;

  private static final Logger LOG =
          LoggerFactory.getLogger(TestHeartbeatMonitor.class);

  @Before
  public void setup() throws Exception {
    injector = Guice.createInjector(new InMemoryDefaultTestModule());
    injector.getInstance(GuiceJpaInitializer.class);
    //injector.getInstance(OrmTestHelper.class).createDefaultData();
    ambariMetaInfo = injector.getInstance(AmbariMetaInfo.class);
    ambariMetaInfo.init();
  }

  @Test public void testHeartbeatLoss() throws AmbariException,InterruptedException,InvalidStateTransitionException{Clusters fsm=injector.getInstance(Clusters.class);String hostname="host1";fsm.addHost(hostname);ActionQueue aq=new ActionQueue();ActionManager am=mock(ActionManager.class);HeartbeatMonitor hm=new HeartbeatMonitor(fsm,aq,am,10);HeartBeatHandler handler=new HeartBeatHandler(fsm,aq,am,injector);Register reg=new Register();reg.setHostname(hostname);reg.setResponseId(12);reg.setTimestamp(System.currentTimeMillis() - 300);HostInfo hi=new HostInfo();hi.setOS("Centos5");reg.setHardwareProfile(hi);handler.handleRegistration(reg);HeartBeat hb=new HeartBeat();hb.setHostname(hostname);hb.setNodeStatus(new HostStatus(HostStatus.Status.HEALTHY,"cool"));hb.setTimestamp(System.currentTimeMillis());hb.setResponseId(12);handler.handleHeartBeat(hb);hm.start();aq.enqueue(hostname,new ExecutionCommand());while (aq.size(hostname) != 0){Thread.sleep(1);}assertEquals(fsm.getHost(hostname).getState(),HostState.HEARTBEAT_LOST);}
}
