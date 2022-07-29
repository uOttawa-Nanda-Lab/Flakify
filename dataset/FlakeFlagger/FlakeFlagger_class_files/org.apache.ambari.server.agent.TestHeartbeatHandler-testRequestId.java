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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;

import junit.framework.Assert;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import org.apache.ambari.server.AmbariException;
import org.apache.ambari.server.Role;
import org.apache.ambari.server.RoleCommand;
import org.apache.ambari.server.actionmanager.ActionDBAccessor;
import org.apache.ambari.server.actionmanager.ActionDBAccessorImpl;
import org.apache.ambari.server.actionmanager.ActionDBInMemoryImpl;
import org.apache.ambari.server.actionmanager.ActionManager;
import org.apache.ambari.server.actionmanager.HostRoleStatus;
import org.apache.ambari.server.actionmanager.Stage;
import org.apache.ambari.server.agent.HostStatus.Status;
import org.apache.ambari.server.api.services.AmbariMetaInfo;
import org.apache.ambari.server.configuration.Configuration;
import org.apache.ambari.server.orm.GuiceJpaInitializer;
import org.apache.ambari.server.orm.InMemoryDefaultTestModule;
import org.apache.ambari.server.state.Cluster;
import org.apache.ambari.server.state.Clusters;
import org.apache.ambari.server.state.Host;
import org.apache.ambari.server.state.HostState;
import org.apache.ambari.server.state.Service;
import org.apache.ambari.server.state.ServiceComponentHost;
import org.apache.ambari.server.state.StackId;
import org.apache.ambari.server.state.State;
import org.apache.ambari.server.state.fsm.InvalidStateTransitionException;
import org.apache.ambari.server.state.svccomphost.ServiceComponentHostStartEvent;
import org.apache.ambari.server.utils.StageUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestHeartbeatHandler {

  private static final Logger log = LoggerFactory.getLogger(TestHeartbeatHandler.class);
  private Injector injector;
  private Clusters clusters;
  long requestId = 23;
  long stageId = 31;

  @Inject
  AmbariMetaInfo metaInfo;
  @Inject
  Configuration config;

  @Before
  public void setup() throws Exception {
    injector = Guice.createInjector(new InMemoryDefaultTestModule());
    injector.getInstance(GuiceJpaInitializer.class);
    clusters = injector.getInstance(Clusters.class);
    injector.injectMembers(this);
    metaInfo.init();
    log.debug("Using server os type=" + config.getServerOsType());
  }

  private void populateActionDB(ActionDBAccessor db, String hostname) {
    Stage s = new Stage(requestId, "/a/b", "cluster1");
    s.setStageId(stageId);
    s.addHostRoleExecutionCommand(hostname, Role.HBASE_MASTER,
        RoleCommand.START,
        new ServiceComponentHostStartEvent(Role.HBASE_MASTER.toString(),
            hostname, System.currentTimeMillis(),
            new HashMap<String, String>()), "cluster1", "HBASE");
    List<Stage> stages = new ArrayList<Stage>();
    stages.add(s);
    db.persistActions(stages);
  }

  @Test public void testRequestId() throws IOException,InvalidStateTransitionException,JsonGenerationException,JAXBException{HeartBeatHandler heartBeatHandler=injector.getInstance(HeartBeatHandler.class);Register register=new Register();register.setHostname("newHost");register.setTimestamp(new Date().getTime());register.setResponseId(123);HostInfo hi=new HostInfo();hi.setHostName("host1");hi.setOS("redhat5");register.setHardwareProfile(hi);RegistrationResponse registrationResponse=heartBeatHandler.handleRegistration(register);assertEquals("ResponseId should start from zero",0L,registrationResponse.getResponseId());HeartBeat heartBeat=constructHeartBeat("newHost",registrationResponse.getResponseId(),Status.HEALTHY);HeartBeatResponse hbResponse=heartBeatHandler.handleHeartBeat(heartBeat);assertEquals("responseId was not incremented",1L,hbResponse.getResponseId());assertTrue("Not cached response returned",hbResponse == heartBeatHandler.handleHeartBeat(heartBeat));heartBeat.setResponseId(1L);hbResponse=heartBeatHandler.handleHeartBeat(heartBeat);assertEquals("responseId was not incremented",2L,hbResponse.getResponseId());assertFalse("Agent is flagged for restart",hbResponse.isRestartAgent());log.debug(StageUtils.jaxbToString(hbResponse));heartBeat.setResponseId(20L);hbResponse=heartBeatHandler.handleHeartBeat(heartBeat);assertTrue("Agent is not flagged for restart",hbResponse.isRestartAgent());log.debug(StageUtils.jaxbToString(hbResponse));}

  private HeartBeat constructHeartBeat(String hostName, long responseId, Status status) {
    HeartBeat heartBeat = new HeartBeat();
    heartBeat.setHostname(hostName);
    heartBeat.setTimestamp(new Date().getTime());
    heartBeat.setResponseId(responseId);
    HostStatus hs = new HostStatus();
    hs.setCause("");
    hs.setStatus(status);
    heartBeat.setNodeStatus(hs);
    heartBeat.setReports(Collections.<CommandReport>emptyList());

    return heartBeat;
  }
}
