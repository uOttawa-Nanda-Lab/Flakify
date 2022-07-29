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

  @Test public void testGetServiceComponentHostsWithFilters() throws AmbariException{clusters.addCluster("c1");Cluster c1=clusters.getCluster("c1");c1.setDesiredStackVersion(new StackId("HDP-0.2"));clusters.addHost("h1");clusters.addHost("h2");clusters.addHost("h3");clusters.getHost("h1").setOsType("centos5");clusters.getHost("h2").setOsType("centos5");clusters.getHost("h3").setOsType("centos5");clusters.getHost("h1").persist();clusters.getHost("h2").persist();clusters.getHost("h3").persist();clusters.mapHostToCluster("h1","c1");clusters.mapHostToCluster("h2","c1");clusters.mapHostToCluster("h3","c1");Service s1=serviceFactory.createNew(c1,"HDFS");Service s2=serviceFactory.createNew(c1,"MAPREDUCE");Service s3=serviceFactory.createNew(c1,"HBASE");c1.addService(s1);c1.addService(s2);c1.addService(s3);s1.setDesiredState(State.INSTALLED);s2.setDesiredState(State.INSTALLED);s1.persist();s2.persist();s3.persist();ServiceComponent sc1=serviceComponentFactory.createNew(s1,"DATANODE");ServiceComponent sc2=serviceComponentFactory.createNew(s1,"NAMENODE");ServiceComponent sc3=serviceComponentFactory.createNew(s3,"HBASE_REGIONSERVER");s1.addServiceComponent(sc1);s1.addServiceComponent(sc2);s3.addServiceComponent(sc3);sc1.setDesiredState(State.UNINSTALLED);sc3.setDesiredState(State.UNINSTALLED);sc1.persist();sc2.persist();sc3.persist();ServiceComponentHost sch1=serviceComponentHostFactory.createNew(sc1,"h1",false);ServiceComponentHost sch2=serviceComponentHostFactory.createNew(sc1,"h2",false);ServiceComponentHost sch3=serviceComponentHostFactory.createNew(sc1,"h3",false);ServiceComponentHost sch4=serviceComponentHostFactory.createNew(sc2,"h1",false);ServiceComponentHost sch5=serviceComponentHostFactory.createNew(sc2,"h2",false);ServiceComponentHost sch6=serviceComponentHostFactory.createNew(sc3,"h3",false);sc1.addServiceComponentHost(sch1);sc1.addServiceComponentHost(sch2);sc1.addServiceComponentHost(sch3);sc2.addServiceComponentHost(sch4);sc2.addServiceComponentHost(sch5);sc3.addServiceComponentHost(sch6);sch1.setDesiredState(State.INSTALLED);sch2.setDesiredState(State.INIT);sch4.setDesiredState(State.INSTALLED);sch5.setDesiredState(State.UNINSTALLED);sch1.persist();sch2.persist();sch3.persist();sch4.persist();sch5.persist();sch6.persist();ServiceComponentHostRequest r=new ServiceComponentHostRequest(null,null,null,null,null,null);try {controller.getHostComponents(Collections.singleton(r));fail("Expected failure for invalid cluster");} catch (Exception e){}r=new ServiceComponentHostRequest(c1.getClusterName(),null,null,null,null,null);Set<ServiceComponentHostResponse> resps=controller.getHostComponents(Collections.singleton(r));Assert.assertEquals(6,resps.size());r=new ServiceComponentHostRequest(c1.getClusterName(),s1.getName(),null,null,null,null);resps=controller.getHostComponents(Collections.singleton(r));Assert.assertEquals(5,resps.size());r=new ServiceComponentHostRequest(c1.getClusterName(),null,sc3.getName(),null,null,null);resps=controller.getHostComponents(Collections.singleton(r));Assert.assertEquals(1,resps.size());r=new ServiceComponentHostRequest(c1.getClusterName(),null,null,"h2",null,null);resps=controller.getHostComponents(Collections.singleton(r));Assert.assertEquals(2,resps.size());r=new ServiceComponentHostRequest(c1.getClusterName(),null,null,null,null,State.UNINSTALLED.toString());resps=controller.getHostComponents(Collections.singleton(r));Assert.assertEquals(1,resps.size());r=new ServiceComponentHostRequest(c1.getClusterName(),s1.getName(),null,null,null,State.INIT.toString());resps=controller.getHostComponents(Collections.singleton(r));Assert.assertEquals(2,resps.size());r=new ServiceComponentHostRequest(c1.getClusterName(),null,sc3.getName(),null,null,State.INSTALLED.toString());resps=controller.getHostComponents(Collections.singleton(r));Assert.assertEquals(0,resps.size());r=new ServiceComponentHostRequest(c1.getClusterName(),null,null,"h2",null,State.INIT.toString());resps=controller.getHostComponents(Collections.singleton(r));Assert.assertEquals(1,resps.size());r=new ServiceComponentHostRequest(c1.getClusterName(),s3.getName(),null,"h1",null,null);resps=controller.getHostComponents(Collections.singleton(r));Assert.assertEquals(0,resps.size());r=new ServiceComponentHostRequest(c1.getClusterName(),s3.getName(),sc3.getName(),"h3",null,State.INSTALLED.toString());resps=controller.getHostComponents(Collections.singleton(r));Assert.assertEquals(0,resps.size());r=new ServiceComponentHostRequest(c1.getClusterName(),s3.getName(),sc3.getName(),"h3",null,null);resps=controller.getHostComponents(Collections.singleton(r));Assert.assertEquals(1,resps.size());ServiceComponentHostRequest r1,r2,r3;r1=new ServiceComponentHostRequest(c1.getClusterName(),null,null,"h3",null,null);r2=new ServiceComponentHostRequest(c1.getClusterName(),s3.getName(),sc3.getName(),"h2",null,null);r3=new ServiceComponentHostRequest(c1.getClusterName(),null,null,"h2",null,null);Set<ServiceComponentHostRequest> reqs=new HashSet<ServiceComponentHostRequest>();reqs.addAll(Arrays.asList(r1,r2,r3));resps=controller.getHostComponents(reqs);Assert.assertEquals(4,resps.size());}

  private void createUser(String userName) throws Exception {
    UserRequest request = new UserRequest(userName);
    request.setPassword("password");

    controller.createUsers(new HashSet<UserRequest>(Collections.singleton(request)));
  }

}
