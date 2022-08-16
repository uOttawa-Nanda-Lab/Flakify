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
package org.apache.ambari.server.actionmanager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.ambari.server.Role;
import org.apache.ambari.server.actionmanager.ActionScheduler.RoleStats;
import org.apache.ambari.server.agent.ActionQueue;
import org.apache.ambari.server.agent.AgentCommand;
import org.apache.ambari.server.agent.ExecutionCommand;
import org.apache.ambari.server.state.Cluster;
import org.apache.ambari.server.state.Clusters;
import org.apache.ambari.server.state.Service;
import org.apache.ambari.server.state.ServiceComponent;
import org.apache.ambari.server.state.ServiceComponentHost;
import org.apache.ambari.server.utils.StageUtils;
import org.apache.ambari.server.utils.TestStageUtils;
import org.junit.Test;

public class TestActionScheduler {

  private List<AgentCommand> waitForQueueSize(String hostname, ActionQueue aq,
      int expectedQueueSize) throws InterruptedException {
    while (true) {
      List<AgentCommand> ac = aq.dequeueAll(hostname);
      if (ac != null) {
        if (ac.size() == expectedQueueSize) {
          return ac;
        } else if (ac.size() > expectedQueueSize) {
          Assert.fail("Expected size : " + expectedQueueSize + " Actual size="
              + ac.size());
        }
      }
      Thread.sleep(100);
    }
  }

  /**
 * Test whether scheduler times out an action
 */@Test public void testActionTimeout() throws Exception{ActionQueue aq=new ActionQueue();Clusters fsm=mock(Clusters.class);Cluster oneClusterMock=mock(Cluster.class);Service serviceObj=mock(Service.class);ServiceComponent scomp=mock(ServiceComponent.class);ServiceComponentHost sch=mock(ServiceComponentHost.class);when(fsm.getCluster(anyString())).thenReturn(oneClusterMock);when(oneClusterMock.getService(anyString())).thenReturn(serviceObj);when(serviceObj.getServiceComponent(anyString())).thenReturn(scomp);when(scomp.getServiceComponentHost(anyString())).thenReturn(sch);when(serviceObj.getCluster()).thenReturn(oneClusterMock);ActionDBAccessor db=new ActionDBInMemoryImpl();String hostname="ahost.ambari.apache.org";List<Stage> stages=new ArrayList<Stage>();Stage s=StageUtils.getATestStage(1,977,hostname);stages.add(s);db.persistActions(stages);ActionScheduler scheduler=new ActionScheduler(100,50,db,aq,fsm,3);scheduler.setTaskTimeoutAdjustment(false);scheduler.start();while (!stages.get(0).getHostRoleStatus(hostname,"NAMENODE").equals(HostRoleStatus.TIMEDOUT)){Thread.sleep(100);}assertEquals(stages.get(0).getHostRoleStatus(hostname,"NAMENODE"),HostRoleStatus.TIMEDOUT);}
}
