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

package org.apache.ambari.server.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.apache.ambari.server.AmbariException;
import org.apache.ambari.server.ClusterNotFoundException;
import org.apache.ambari.server.Role;
import org.apache.ambari.server.RoleCommand;
import org.apache.ambari.server.ServiceNotFoundException;
import org.apache.ambari.server.actionmanager.*;
import org.apache.ambari.server.api.services.AmbariMetaInfo;
import org.apache.ambari.server.orm.GuiceJpaInitializer;
import org.apache.ambari.server.orm.InMemoryDefaultTestModule;
import org.apache.ambari.server.orm.dao.RoleDAO;
import org.apache.ambari.server.orm.entities.RoleEntity;
import org.apache.ambari.server.security.authorization.Users;
import org.apache.ambari.server.state.Cluster;
import org.apache.ambari.server.state.Clusters;
import org.apache.ambari.server.state.Config;
import org.apache.ambari.server.state.ConfigImpl;
import org.apache.ambari.server.state.Host;
import org.apache.ambari.server.state.Service;
import org.apache.ambari.server.state.ServiceComponent;
import org.apache.ambari.server.state.ServiceComponentFactory;
import org.apache.ambari.server.state.ServiceComponentHost;
import org.apache.ambari.server.state.ServiceComponentHostFactory;
import org.apache.ambari.server.state.ServiceFactory;
import org.apache.ambari.server.state.StackId;
import org.apache.ambari.server.state.State;
import org.apache.ambari.server.state.svccomphost.ServiceComponentHostStartEvent;
import org.apache.ambari.server.utils.StageUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;

public class AmbariManagementControllerTest {

  private static final Logger LOG =
      LoggerFactory.getLogger(AmbariManagementControllerTest.class);

  private AmbariManagementController controller;
  private Clusters clusters;
  private ActionDBAccessor actionDB;
  private Injector injector;
  private ServiceFactory serviceFactory;
  private ServiceComponentFactory serviceComponentFactory;
  private ServiceComponentHostFactory serviceComponentHostFactory;
  private AmbariMetaInfo ambariMetaInfo;
  private Users users;

  @Before
  public void setup() throws Exception {
    injector = Guice.createInjector(new InMemoryDefaultTestModule());
    injector.getInstance(GuiceJpaInitializer.class);
    clusters = injector.getInstance(Clusters.class);
    actionDB = injector.getInstance(ActionDBAccessor.class);
    controller = injector.getInstance(AmbariManagementController.class);
    serviceFactory = injector.getInstance(ServiceFactory.class);
    serviceComponentFactory = injector.getInstance(
        ServiceComponentFactory.class);
    serviceComponentHostFactory = injector.getInstance(
        ServiceComponentHostFactory.class);
    ambariMetaInfo = injector.getInstance(AmbariMetaInfo.class);
    ambariMetaInfo.init();
    users = injector.getInstance(Users.class);
  }

  private void createCluster(String clusterName) throws AmbariException {
    ClusterRequest r = new ClusterRequest(null, clusterName, "HDP-0.1", null);
    controller.createCluster(r);
  }

  private void createService(String clusterName,
      String serviceName, State desiredState) throws AmbariException {
    String dStateStr = null;
    if (desiredState != null) {
      dStateStr = desiredState.toString();
    }
    ServiceRequest r1 = new ServiceRequest(clusterName, serviceName, null,
        dStateStr);
    Set<ServiceRequest> requests = new HashSet<ServiceRequest>();
    requests.add(r1);
    controller.createServices(requests);
  }

  private void createServiceComponent(String clusterName,
      String serviceName, String componentName, State desiredState)
          throws AmbariException {
    String dStateStr = null;
    if (desiredState != null) {
      dStateStr = desiredState.toString();
    }
    ServiceComponentRequest r = new ServiceComponentRequest(clusterName,
        serviceName, componentName, null, dStateStr);
    Set<ServiceComponentRequest> requests =
        new HashSet<ServiceComponentRequest>();
    requests.add(r);
    controller.createComponents(requests);
  }

  private void createServiceComponentHost(String clusterName,
      String serviceName, String componentName, String hostname,
      State desiredState) throws AmbariException {
    String dStateStr = null;
    if (desiredState != null) {
      dStateStr = desiredState.toString();
    }
    ServiceComponentHostRequest r = new ServiceComponentHostRequest(clusterName,
        serviceName, componentName, hostname, null, dStateStr);
    Set<ServiceComponentHostRequest> requests =
        new HashSet<ServiceComponentHostRequest>();
    requests.add(r);
    controller.createHostComponents(requests);
  }

  @Test public void testInstallAndStartService() throws Exception{testCreateServiceComponentHostSimple();String clusterName="foo1";String serviceName="HDFS";Cluster cluster=clusters.getCluster(clusterName);Service s1=cluster.getService(serviceName);Map<String, Config> configs=new HashMap<String, Config>();Map<String, String> properties=new HashMap<String, String>();properties.put("a","a1");properties.put("b","b1");Config c1=new ConfigImpl(cluster,"hdfs-site",properties,injector);properties.put("c","c1");properties.put("d","d1");Config c2=new ConfigImpl(cluster,"core-site",properties,injector);Config c3=new ConfigImpl(cluster,"foo-site",properties,injector);c1.setVersionTag("v1");c2.setVersionTag("v1");c3.setVersionTag("v1");cluster.addDesiredConfig(c1);cluster.addDesiredConfig(c2);cluster.addDesiredConfig(c3);c1.persist();c2.persist();c3.persist();configs.put(c1.getType(),c1);configs.put(c2.getType(),c2);s1.updateDesiredConfigs(configs);s1.persist();ServiceRequest r=new ServiceRequest(clusterName,serviceName,null,State.INSTALLED.toString());Set<ServiceRequest> requests=new HashSet<ServiceRequest>();requests.add(r);RequestStatusResponse trackAction=controller.updateServices(requests);Assert.assertEquals(State.INSTALLED,clusters.getCluster(clusterName).getService(serviceName).getDesiredState());for (ServiceComponent sc:clusters.getCluster(clusterName).getService(serviceName).getServiceComponents().values()){Assert.assertEquals(State.INSTALLED,sc.getDesiredState());for (ServiceComponentHost sch:sc.getServiceComponentHosts().values()){Assert.assertEquals(State.INSTALLED,sch.getDesiredState());Assert.assertEquals(State.INIT,sch.getState());}}List<ShortTaskStatus> taskStatuses=trackAction.getTasks();Assert.assertEquals(5,taskStatuses.size());boolean foundH1NN=false;boolean foundH1DN=false;boolean foundH2DN=false;boolean foundH1CLT=false;boolean foundH2CLT=false;for (ShortTaskStatus taskStatus:taskStatuses){LOG.debug("Task dump :" + taskStatus.toString());Assert.assertEquals(RoleCommand.INSTALL.toString(),taskStatus.getCommand());Assert.assertEquals(HostRoleStatus.PENDING.toString(),taskStatus.getStatus());if (taskStatus.getHostName().equals("h1")){if (Role.NAMENODE.toString().equals(taskStatus.getRole())){foundH1NN=true;} else if (Role.DATANODE.toString().equals(taskStatus.getRole())){foundH1DN=true;} else if (Role.HDFS_CLIENT.toString().equals(taskStatus.getRole())){foundH1CLT=true;} else {fail("Found invalid role for host h1");}} else if (taskStatus.getHostName().equals("h2")){if (Role.DATANODE.toString().equals(taskStatus.getRole())){foundH2DN=true;} else if (Role.HDFS_CLIENT.toString().equals(taskStatus.getRole())){foundH2CLT=true;} else {fail("Found invalid role for host h2");}} else {fail("Found invalid host in task list");}}Assert.assertTrue(foundH1DN && foundH1NN && foundH2DN && foundH1CLT && foundH2CLT);List<Stage> stages=actionDB.getAllStages(trackAction.getRequestId());Assert.assertEquals(1,stages.size());for (Stage stage:stages){LOG.info("Stage Details for Install Service" + ", stageId=" + stage.getStageId() + ", actionId=" + stage.getActionId());for (String host:stage.getHosts()){for (ExecutionCommandWrapper ecw:stage.getExecutionCommands(host)){Assert.assertFalse(ecw.getExecutionCommand().getHostLevelParams().get("repo_info").isEmpty());LOG.info("Dumping host action details" + ", stageId=" + stage.getStageId() + ", actionId=" + stage.getActionId() + ", commandDetails=" + StageUtils.jaxbToString(ecw.getExecutionCommand()));}}}RequestStatusRequest statusRequest=new RequestStatusRequest(trackAction.getRequestId(),null);Set<RequestStatusResponse> statusResponses=controller.getRequestStatus(statusRequest);Assert.assertEquals(1,statusResponses.size());RequestStatusResponse statusResponse=statusResponses.iterator().next();Assert.assertNotNull(statusResponse);Assert.assertEquals(trackAction.getRequestId(),statusResponse.getRequestId());Assert.assertEquals(5,statusResponse.getTasks().size());Set<TaskStatusRequest> taskRequests=new HashSet<TaskStatusRequest>();TaskStatusRequest t1,t2;t1=new TaskStatusRequest();t2=new TaskStatusRequest();t1.setRequestId(trackAction.getRequestId());taskRequests.add(t1);Set<TaskStatusResponse> taskResponses=controller.getTaskStatus(taskRequests);Assert.assertEquals(5,taskResponses.size());t1.setTaskId(1L);t2.setRequestId(trackAction.getRequestId());t2.setTaskId(2L);taskRequests.clear();taskRequests.add(t1);taskRequests.add(t2);taskResponses=controller.getTaskStatus(taskRequests);Assert.assertEquals(2,taskResponses.size());for (ServiceComponent sc:clusters.getCluster(clusterName).getService(serviceName).getServiceComponents().values()){for (ServiceComponentHost sch:sc.getServiceComponentHosts().values()){sch.setState(State.INSTALLED);}}r=new ServiceRequest(clusterName,serviceName,null,State.STARTED.toString());requests.clear();requests.add(r);trackAction=controller.updateServices(requests);Assert.assertEquals(State.STARTED,clusters.getCluster(clusterName).getService(serviceName).getDesiredState());for (ServiceComponent sc:clusters.getCluster(clusterName).getService(serviceName).getServiceComponents().values()){if (sc.getName().equals("HDFS_CLIENT")){Assert.assertEquals(State.INSTALLED,sc.getDesiredState());} else {Assert.assertEquals(State.STARTED,sc.getDesiredState());}for (ServiceComponentHost sch:sc.getServiceComponentHosts().values()){if (sch.getServiceComponentName().equals("HDFS_CLIENT")){Assert.assertEquals(State.INSTALLED,sch.getDesiredState());} else {Assert.assertEquals(State.STARTED,sch.getDesiredState());}}}stages=actionDB.getAllStages(trackAction.getRequestId());Assert.assertEquals(2,stages.size());for (Stage stage:stages){LOG.info("Stage Details for Start Service" + ", stageId=" + stage.getStageId() + ", actionId=" + stage.getActionId());for (String host:stage.getHosts()){LOG.info("Dumping host action details" + ", stageId=" + stage.getStageId() + ", actionId=" + stage.getActionId() + ", commandDetails=" + StageUtils.jaxbToString(stage.getExecutionCommands(host).get(0)));}}StringBuilder sb=new StringBuilder();clusters.debugDump(sb);LOG.info("Cluster Dump: " + sb.toString());statusRequest=new RequestStatusRequest(null,null);statusResponses=controller.getRequestStatus(statusRequest);Assert.assertEquals(2,statusResponses.size());int counter=0;for (ServiceComponent sc:clusters.getCluster(clusterName).getService(serviceName).getServiceComponents().values()){for (ServiceComponentHost sch:sc.getServiceComponentHosts().values()){if (sc.isClientComponent()){sch.setState(State.INSTALLED);} else {++counter;switch (counter % 1){case 0:sch.setState(State.START_FAILED);break;case 1:sch.setState(State.STOP_FAILED);break;case 2:sch.setState(State.STARTED);break;}}}}r=new ServiceRequest(clusterName,serviceName,null,State.INSTALLED.toString());requests.clear();requests.add(r);trackAction=controller.updateServices(requests);Assert.assertEquals(State.INSTALLED,clusters.getCluster(clusterName).getService(serviceName).getDesiredState());for (ServiceComponent sc:clusters.getCluster(clusterName).getService(serviceName).getServiceComponents().values()){Assert.assertEquals(State.INSTALLED,sc.getDesiredState());for (ServiceComponentHost sch:sc.getServiceComponentHosts().values()){Assert.assertEquals(State.INSTALLED,sch.getDesiredState());}}stages=actionDB.getAllStages(trackAction.getRequestId());for (Stage stage:stages){LOG.info("Stage Details for Stop Service : " + stage.toString());}Assert.assertEquals(1,stages.size());}

  private void createUser(String userName) throws Exception {
    UserRequest request = new UserRequest(userName);
    request.setPassword("password");

    controller.createUsers(new HashSet<UserRequest>(Collections.singleton(request)));
  }

}
