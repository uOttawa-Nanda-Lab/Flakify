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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import org.apache.ambari.server.AmbariException;
import org.apache.ambari.server.Role;
import org.apache.ambari.server.RoleCommand;
import org.apache.ambari.server.agent.ActionQueue;
import org.apache.ambari.server.agent.CommandReport;
import org.apache.ambari.server.orm.GuiceJpaInitializer;
import org.apache.ambari.server.orm.InMemoryDefaultTestModule;
import org.apache.ambari.server.orm.dao.ExecutionCommandDAO;
import org.apache.ambari.server.orm.dao.HostRoleCommandDAO;
import org.apache.ambari.server.orm.entities.HostRoleCommandEntity;
import org.apache.ambari.server.state.Clusters;
import org.apache.ambari.server.state.svccomphost.ServiceComponentHostStartEvent;
import org.apache.ambari.server.utils.StageUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;

import static org.junit.Assert.*;

public class TestActionDBAccessorImpl {
  private static final Logger log = LoggerFactory.getLogger(TestActionDBAccessorImpl.class);

  private long requestId = 23;
  private long stageId = 31;
  private String hostName = "host1";
  private String clusterName = "cluster1";
  private Injector injector;
  ActionDBAccessor db;
  ActionManager am;

  @Inject
  private Clusters clusters;
  @Inject
  private ExecutionCommandDAO executionCommandDAO;
  @Inject
  private HostRoleCommandDAO hostRoleCommandDAO;

  @Before
  public void setup() throws AmbariException {
    injector = Guice.createInjector(new InMemoryDefaultTestModule());
    injector.getInstance(GuiceJpaInitializer.class);
    injector.injectMembers(this);
    clusters.addHost(hostName);
    clusters.getHost(hostName).persist();
    clusters.addCluster(clusterName);
    db = injector.getInstance(ActionDBAccessorImpl.class);
    am = new ActionManager(5000, 1200000, new ActionQueue(), clusters, db);
  }

  @Test public void testActionResponse(){String hostname="host1";populateActionDB(db,hostname,requestId,stageId);Stage stage=db.getAllStages(requestId).get(0);Assert.assertEquals(stageId,stage.getStageId());stage.setHostRoleStatus(hostname,"HBASE_MASTER",HostRoleStatus.QUEUED);db.hostRoleScheduled(stage,hostname,"HBASE_MASTER");List<CommandReport> reports=new ArrayList<CommandReport>();CommandReport cr=new CommandReport();cr.setTaskId(1);cr.setActionId(StageUtils.getActionId(requestId,stageId));cr.setRole("HBASE_MASTER");cr.setStatus("COMPLETED");cr.setStdErr("");cr.setStdOut("");cr.setExitCode(215);reports.add(cr);am.processTaskResponse(hostname,reports);assertEquals(215,am.getAction(requestId,stageId).getExitCode(hostname,"HBASE_MASTER"));assertEquals(HostRoleStatus.COMPLETED,am.getAction(requestId,stageId).getHostRoleStatus(hostname,"HBASE_MASTER"));Stage s=db.getAllStages(requestId).get(0);assertEquals(HostRoleStatus.COMPLETED,s.getHostRoleStatus(hostname,"HBASE_MASTER"));}
  
  private void populateActionDB(ActionDBAccessor db, String hostname,
      long requestId, long stageId) {
    Stage s = new Stage(requestId, "/a/b", "cluster1");
    s.setStageId(stageId);
    s.addHostRoleExecutionCommand(hostname, Role.HBASE_MASTER,
        RoleCommand.START,
        new ServiceComponentHostStartEvent(Role.HBASE_MASTER.toString(),
            hostname, System.currentTimeMillis(),
            new HashMap<String, String>()), "cluster1", "HBASE");
    s.addHostRoleExecutionCommand(
        hostname,
        Role.HBASE_REGIONSERVER,
        RoleCommand.START,
        new ServiceComponentHostStartEvent(Role.HBASE_REGIONSERVER
            .toString(), hostname, System.currentTimeMillis(),
            new HashMap<String, String>()), "cluster1", "HBASE");
    List<Stage> stages = new ArrayList<Stage>();
    stages.add(s);
    db.persistActions(stages);
  }
}
