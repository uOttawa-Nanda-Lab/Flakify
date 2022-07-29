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

  private void createUser(String userName) throws Exception {
    UserRequest request = new UserRequest(userName);
    request.setPassword("password");

    controller.createUsers(new HashSet<UserRequest>(Collections.singleton(request)));
  }

  @Test public void testConfigUpdates() throws AmbariException{String clusterName="foo1";createCluster(clusterName);clusters.getCluster(clusterName).setDesiredStackVersion(new StackId("HDP-0.1"));String serviceName="HDFS";createService(clusterName,serviceName,null);String componentName1="NAMENODE";String componentName2="DATANODE";String componentName3="HDFS_CLIENT";createServiceComponent(clusterName,serviceName,componentName1,State.INIT);createServiceComponent(clusterName,serviceName,componentName2,State.INIT);createServiceComponent(clusterName,serviceName,componentName3,State.INIT);String host1="h1";clusters.addHost(host1);clusters.getHost("h1").setOsType("centos5");clusters.getHost("h1").persist();String host2="h2";clusters.addHost(host2);clusters.getHost("h2").setOsType("centos6");clusters.getHost("h2").persist();clusters.mapHostToCluster(host1,clusterName);clusters.mapHostToCluster(host2,clusterName);createServiceComponentHost(clusterName,null,componentName1,host1,null);createServiceComponentHost(clusterName,serviceName,componentName2,host1,null);createServiceComponentHost(clusterName,serviceName,componentName2,host2,null);createServiceComponentHost(clusterName,serviceName,componentName3,host1,null);createServiceComponentHost(clusterName,serviceName,componentName3,host2,null);Assert.assertNotNull(clusters.getCluster(clusterName).getService(serviceName).getServiceComponent(componentName1).getServiceComponentHost(host1));Assert.assertNotNull(clusters.getCluster(clusterName).getService(serviceName).getServiceComponent(componentName2).getServiceComponentHost(host1));Assert.assertNotNull(clusters.getCluster(clusterName).getService(serviceName).getServiceComponent(componentName2).getServiceComponentHost(host2));Assert.assertNotNull(clusters.getCluster(clusterName).getService(serviceName).getServiceComponent(componentName3).getServiceComponentHost(host1));Assert.assertNotNull(clusters.getCluster(clusterName).getService(serviceName).getServiceComponent(componentName3).getServiceComponentHost(host2));Map<String, String> configs=new HashMap<String, String>();configs.put("a","b");ConfigurationRequest cr1,cr2,cr3,cr4,cr5,cr6,cr7,cr8;cr1=new ConfigurationRequest(clusterName,"typeA","v1",configs);cr2=new ConfigurationRequest(clusterName,"typeB","v1",configs);cr3=new ConfigurationRequest(clusterName,"typeC","v1",configs);cr4=new ConfigurationRequest(clusterName,"typeD","v1",configs);cr5=new ConfigurationRequest(clusterName,"typeA","v2",configs);cr6=new ConfigurationRequest(clusterName,"typeB","v2",configs);cr7=new ConfigurationRequest(clusterName,"typeC","v2",configs);cr8=new ConfigurationRequest(clusterName,"typeE","v1",configs);controller.createConfiguration(cr1);controller.createConfiguration(cr2);controller.createConfiguration(cr3);controller.createConfiguration(cr4);controller.createConfiguration(cr5);controller.createConfiguration(cr6);controller.createConfiguration(cr7);controller.createConfiguration(cr8);Cluster cluster=clusters.getCluster(clusterName);Service s=cluster.getService(serviceName);ServiceComponent sc1=s.getServiceComponent(componentName1);ServiceComponent sc2=s.getServiceComponent(componentName2);ServiceComponentHost sch1=sc1.getServiceComponentHost(host1);Set<ServiceComponentHostRequest> schReqs=new HashSet<ServiceComponentHostRequest>();Set<ServiceComponentRequest> scReqs=new HashSet<ServiceComponentRequest>();Set<ServiceRequest> sReqs=new HashSet<ServiceRequest>();Map<String, String> configVersions=new HashMap<String, String>();configVersions.clear();configVersions.put("typeA","v1");configVersions.put("typeB","v1");configVersions.put("typeC","v1");schReqs.clear();schReqs.add(new ServiceComponentHostRequest(clusterName,serviceName,componentName1,host1,configVersions,null));Assert.assertNull(controller.updateHostComponents(schReqs));Assert.assertEquals(0,s.getDesiredConfigs().size());Assert.assertEquals(0,sc1.getDesiredConfigs().size());Assert.assertEquals(3,sch1.getDesiredConfigs().size());configVersions.clear();configVersions.put("typeC","v1");configVersions.put("typeD","v1");scReqs.clear();scReqs.add(new ServiceComponentRequest(clusterName,serviceName,componentName2,configVersions,null));Assert.assertNull(controller.updateComponents(scReqs));Assert.assertEquals(0,s.getDesiredConfigs().size());Assert.assertEquals(0,sc1.getDesiredConfigs().size());Assert.assertEquals(2,sc2.getDesiredConfigs().size());Assert.assertEquals(3,sch1.getDesiredConfigs().size());configVersions.clear();configVersions.put("typeA","v2");configVersions.put("typeC","v2");configVersions.put("typeE","v1");sReqs.clear();sReqs.add(new ServiceRequest(clusterName,serviceName,configVersions,null));Assert.assertNull(controller.updateServices(sReqs));Assert.assertEquals(3,s.getDesiredConfigs().size());Assert.assertEquals(3,sc1.getDesiredConfigs().size());Assert.assertEquals(4,sc2.getDesiredConfigs().size());Assert.assertEquals(4,sch1.getDesiredConfigs().size());Assert.assertEquals("v2",s.getDesiredConfigs().get("typeA").getVersionTag());Assert.assertEquals("v2",s.getDesiredConfigs().get("typeC").getVersionTag());Assert.assertEquals("v1",s.getDesiredConfigs().get("typeE").getVersionTag());Assert.assertEquals("v2",sc1.getDesiredConfigs().get("typeA").getVersionTag());Assert.assertEquals("v2",sc1.getDesiredConfigs().get("typeC").getVersionTag());Assert.assertEquals("v1",sc1.getDesiredConfigs().get("typeE").getVersionTag());Assert.assertEquals("v2",sc2.getDesiredConfigs().get("typeA").getVersionTag());Assert.assertEquals("v2",sc2.getDesiredConfigs().get("typeC").getVersionTag());Assert.assertEquals("v1",sc2.getDesiredConfigs().get("typeE").getVersionTag());Assert.assertEquals("v1",sc2.getDesiredConfigs().get("typeD").getVersionTag());Assert.assertEquals("v2",sch1.getDesiredConfigs().get("typeA").getVersionTag());Assert.assertEquals("v2",sch1.getDesiredConfigs().get("typeC").getVersionTag());Assert.assertEquals("v1",sch1.getDesiredConfigs().get("typeE").getVersionTag());Assert.assertEquals("v1",sch1.getDesiredConfigs().get("typeB").getVersionTag());configVersions.clear();configVersions.put("typeA","v1");configVersions.put("typeB","v1");configVersions.put("typeC","v1");schReqs.clear();schReqs.add(new ServiceComponentHostRequest(clusterName,serviceName,componentName1,host1,configVersions,null));Assert.assertNull(controller.updateHostComponents(schReqs));Assert.assertEquals(3,s.getDesiredConfigs().size());Assert.assertEquals(3,sc1.getDesiredConfigs().size());Assert.assertEquals(4,sc2.getDesiredConfigs().size());Assert.assertEquals(4,sch1.getDesiredConfigs().size());Assert.assertEquals("v1",sch1.getDesiredConfigs().get("typeA").getVersionTag());Assert.assertEquals("v1",sch1.getDesiredConfigs().get("typeC").getVersionTag());Assert.assertEquals("v1",sch1.getDesiredConfigs().get("typeE").getVersionTag());Assert.assertEquals("v1",sch1.getDesiredConfigs().get("typeB").getVersionTag());configVersions.clear();configVersions.put("typeC","v2");configVersions.put("typeD","v1");scReqs.clear();scReqs.add(new ServiceComponentRequest(clusterName,serviceName,componentName1,configVersions,null));Assert.assertNull(controller.updateComponents(scReqs));Assert.assertEquals(3,s.getDesiredConfigs().size());Assert.assertEquals(4,sc1.getDesiredConfigs().size());Assert.assertEquals(4,sc2.getDesiredConfigs().size());Assert.assertEquals(5,sch1.getDesiredConfigs().size());Assert.assertEquals("v2",sc1.getDesiredConfigs().get("typeA").getVersionTag());Assert.assertEquals("v2",sc1.getDesiredConfigs().get("typeC").getVersionTag());Assert.assertEquals("v1",sc2.getDesiredConfigs().get("typeD").getVersionTag());Assert.assertEquals("v1",sc1.getDesiredConfigs().get("typeE").getVersionTag());Assert.assertEquals("v1",sch1.getDesiredConfigs().get("typeA").getVersionTag());Assert.assertEquals("v2",sch1.getDesiredConfigs().get("typeC").getVersionTag());Assert.assertEquals("v1",sch1.getDesiredConfigs().get("typeD").getVersionTag());Assert.assertEquals("v1",sch1.getDesiredConfigs().get("typeE").getVersionTag());Assert.assertEquals("v1",sch1.getDesiredConfigs().get("typeB").getVersionTag());}

}
