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

  @Test public void testCreateServiceComponentHostWithInvalidRequest() throws AmbariException{Set<ServiceComponentHostRequest> set1=new HashSet<ServiceComponentHostRequest>();try {set1.clear();ServiceComponentHostRequest rInvalid=new ServiceComponentHostRequest(null,null,null,null,null,null);set1.add(rInvalid);controller.createHostComponents(set1);fail("Expected failure for invalid requests");} catch (IllegalArgumentException e){}try {set1.clear();ServiceComponentHostRequest rInvalid=new ServiceComponentHostRequest("foo",null,null,null,null,null);set1.add(rInvalid);controller.createHostComponents(set1);fail("Expected failure for invalid requests");} catch (IllegalArgumentException e){}try {set1.clear();ServiceComponentHostRequest rInvalid=new ServiceComponentHostRequest("foo","HDFS",null,null,null,null);set1.add(rInvalid);controller.createHostComponents(set1);fail("Expected failure for invalid requests");} catch (IllegalArgumentException e){}try {set1.clear();ServiceComponentHostRequest rInvalid=new ServiceComponentHostRequest("foo","HDFS","NAMENODE",null,null,null);set1.add(rInvalid);controller.createHostComponents(set1);fail("Expected failure for invalid requests");} catch (IllegalArgumentException e){}try {set1.clear();ServiceComponentHostRequest rInvalid=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h1",null,null);set1.add(rInvalid);controller.createHostComponents(set1);fail("Expected failure for invalid cluster");} catch (ClusterNotFoundException e){}clusters.addCluster("foo");clusters.addCluster("c1");clusters.addCluster("c2");Cluster foo=clusters.getCluster("foo");Cluster c1=clusters.getCluster("c1");Cluster c2=clusters.getCluster("c2");foo.setDesiredStackVersion(new StackId("HDP-0.2"));c1.setDesiredStackVersion(new StackId("HDP-0.2"));c2.setDesiredStackVersion(new StackId("HDP-0.2"));try {set1.clear();ServiceComponentHostRequest rInvalid=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h1",null,null);set1.add(rInvalid);controller.createHostComponents(set1);fail("Expected failure for invalid service");} catch (AmbariException e){}Service s1=serviceFactory.createNew(foo,"HDFS");foo.addService(s1);s1.persist();Service s2=serviceFactory.createNew(c1,"HDFS");c1.addService(s2);s2.persist();Service s3=serviceFactory.createNew(c2,"HDFS");c2.addService(s3);s3.persist();try {set1.clear();ServiceComponentHostRequest rInvalid=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h1",null,null);set1.add(rInvalid);controller.createHostComponents(set1);fail("Expected failure for invalid service");} catch (AmbariException e){}ServiceComponent sc1=serviceComponentFactory.createNew(s1,"NAMENODE");s1.addServiceComponent(sc1);sc1.persist();ServiceComponent sc2=serviceComponentFactory.createNew(s2,"NAMENODE");s2.addServiceComponent(sc2);sc2.persist();ServiceComponent sc3=serviceComponentFactory.createNew(s3,"NAMENODE");s3.addServiceComponent(sc3);sc3.persist();try {set1.clear();ServiceComponentHostRequest rInvalid=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h1",null,null);set1.add(rInvalid);controller.createHostComponents(set1);fail("Expected failure for invalid host");} catch (AmbariException e){}clusters.addHost("h1");Host h1=clusters.getHost("h1");h1.setIPv4("ipv41");h1.setIPv6("ipv61");h1.setOsType("centos6");h1.persist();clusters.addHost("h2");Host h2=clusters.getHost("h2");h2.setIPv4("ipv42");h2.setIPv6("ipv62");h2.setOsType("centos6");h2.persist();clusters.addHost("h3");Host h3=clusters.getHost("h3");h3.setIPv4("ipv43");h3.setIPv6("ipv63");h3.setOsType("centos6");h3.persist();try {set1.clear();ServiceComponentHostRequest rInvalid=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h1",null,null);set1.add(rInvalid);controller.createHostComponents(set1);fail("Expected failure for invalid host cluster mapping");} catch (AmbariException e){}Set<String> hostnames=new HashSet<String>();hostnames.add("h1");hostnames.add("h2");hostnames.add("h3");clusters.mapHostsToCluster(hostnames,"foo");clusters.mapHostsToCluster(hostnames,"c1");clusters.mapHostsToCluster(hostnames,"c2");set1.clear();ServiceComponentHostRequest valid=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h1",null,null);set1.add(valid);controller.createHostComponents(set1);try {set1.clear();ServiceComponentHostRequest rInvalid1=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h2",null,null);ServiceComponentHostRequest rInvalid2=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h2",null,null);set1.add(rInvalid1);set1.add(rInvalid2);controller.createHostComponents(set1);fail("Expected failure for dup requests");} catch (IllegalArgumentException e){}try {set1.clear();ServiceComponentHostRequest rInvalid1=new ServiceComponentHostRequest("c1","HDFS","NAMENODE","h2",null,null);ServiceComponentHostRequest rInvalid2=new ServiceComponentHostRequest("c2","HDFS","NAMENODE","h3",null,null);set1.add(rInvalid1);set1.add(rInvalid2);controller.createHostComponents(set1);fail("Expected failure for multiple clusters");} catch (IllegalArgumentException e){}try {set1.clear();ServiceComponentHostRequest rInvalid1=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h1",null,null);ServiceComponentHostRequest rInvalid2=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h2",null,null);set1.add(rInvalid1);set1.add(rInvalid2);controller.createHostComponents(set1);fail("Expected failure for already existing");} catch (IllegalArgumentException e){}Assert.assertEquals(1,foo.getServiceComponentHosts("h1").size());Assert.assertEquals(0,foo.getServiceComponentHosts("h2").size());Assert.assertEquals(0,foo.getServiceComponentHosts("h3").size());set1.clear();ServiceComponentHostRequest valid1=new ServiceComponentHostRequest("c1","HDFS","NAMENODE","h1",null,null);set1.add(valid1);controller.createHostComponents(set1);set1.clear();ServiceComponentHostRequest valid2=new ServiceComponentHostRequest("c2","HDFS","NAMENODE","h1",null,null);set1.add(valid2);controller.createHostComponents(set1);Assert.assertEquals(1,foo.getServiceComponentHosts("h1").size());Assert.assertEquals(1,c1.getServiceComponentHosts("h1").size());Assert.assertEquals(1,c2.getServiceComponentHosts("h1").size());}

  private void createUser(String userName) throws Exception {
    UserRequest request = new UserRequest(userName);
    request.setPassword("password");

    controller.createUsers(new HashSet<UserRequest>(Collections.singleton(request)));
  }

}
