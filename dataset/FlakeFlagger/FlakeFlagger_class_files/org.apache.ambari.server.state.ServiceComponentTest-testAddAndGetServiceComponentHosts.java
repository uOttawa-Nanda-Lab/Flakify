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

package org.apache.ambari.server.state;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.apache.ambari.server.AmbariException;
import org.apache.ambari.server.api.services.AmbariMetaInfo;
import org.apache.ambari.server.controller.ServiceComponentResponse;
import org.apache.ambari.server.orm.GuiceJpaInitializer;
import org.apache.ambari.server.orm.InMemoryDefaultTestModule;
import org.apache.ambari.server.orm.dao.HostComponentDesiredStateDAO;
import org.apache.ambari.server.orm.dao.HostComponentStateDAO;
import org.apache.ambari.server.orm.dao.ServiceComponentDesiredStateDAO;
import org.apache.ambari.server.orm.entities.HostComponentDesiredStateEntity;
import org.apache.ambari.server.orm.entities.HostComponentDesiredStateEntityPK;
import org.apache.ambari.server.orm.entities.HostComponentStateEntity;
import org.apache.ambari.server.orm.entities.HostComponentStateEntityPK;
import org.apache.ambari.server.orm.entities.ServiceComponentDesiredStateEntity;
import org.apache.ambari.server.orm.entities.ServiceComponentDesiredStateEntityPK;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;

public class ServiceComponentTest {

  private Clusters clusters;
  private Cluster cluster;
  private Service service;
  private String clusterName;
  private String serviceName;
  private Injector injector;
  private ServiceFactory serviceFactory;
  private ServiceComponentFactory serviceComponentFactory;
  private ServiceComponentHostFactory serviceComponentHostFactory;
  private AmbariMetaInfo metaInfo;

  @Before
  public void setup() throws Exception {
    injector = Guice.createInjector(new InMemoryDefaultTestModule());
    injector.getInstance(GuiceJpaInitializer.class);
    clusters = injector.getInstance(Clusters.class);
    serviceFactory = injector.getInstance(ServiceFactory.class);
    serviceComponentFactory = injector.getInstance(
        ServiceComponentFactory.class);
    serviceComponentHostFactory = injector.getInstance(
        ServiceComponentHostFactory.class);
    metaInfo = injector.getInstance(AmbariMetaInfo.class);
    metaInfo.init();

    clusterName = "foo";
    serviceName = "HDFS";
    clusters.addCluster(clusterName);
    cluster = clusters.getCluster(clusterName);
    cluster.setDesiredStackVersion(new StackId("HDP-0.1"));
    Assert.assertNotNull(cluster);
    Service s = serviceFactory.createNew(cluster, serviceName);
    cluster.addService(s);
    s.persist();
    service = cluster.getService(serviceName);
    Assert.assertNotNull(service);
  }

  private void addHostToCluster(String hostname,
      String clusterName) throws AmbariException {
    clusters.addHost(hostname);
    Host h = clusters.getHost(hostname);
    h.setIPv4(hostname + "ipv4");
    h.setIPv6(hostname + "ipv6");
    h.setOsType("centos6");
    h.persist();
    clusters.mapHostToCluster(hostname, clusterName);
  }

  @Test public void testAddAndGetServiceComponentHosts() throws AmbariException{String componentName="NAMENODE";ServiceComponent component=serviceComponentFactory.createNew(service,componentName);service.addServiceComponent(component);component.persist();ServiceComponent sc=service.getServiceComponent(componentName);Assert.assertNotNull(sc);Assert.assertTrue(sc.getServiceComponentHosts().isEmpty());try {serviceComponentHostFactory.createNew(sc,"h1",false);fail("Expected error for invalid host");} catch (Exception e){}addHostToCluster("h1",service.getCluster().getClusterName());addHostToCluster("h2",service.getCluster().getClusterName());addHostToCluster("h3",service.getCluster().getClusterName());ServiceComponentHost sch1=serviceComponentHostFactory.createNew(sc,"h1",false);ServiceComponentHost sch2=serviceComponentHostFactory.createNew(sc,"h2",false);ServiceComponentHost failSch=serviceComponentHostFactory.createNew(sc,"h2",false);Map<String, ServiceComponentHost> compHosts=new HashMap<String, ServiceComponentHost>();compHosts.put("h1",sch1);compHosts.put("h2",sch2);compHosts.put("h3",failSch);try {sc.addServiceComponentHosts(compHosts);fail("Expected error for dups");} catch (Exception e){}Assert.assertTrue(sc.getServiceComponentHosts().isEmpty());compHosts.remove("h3");sc.addServiceComponentHosts(compHosts);Assert.assertEquals(2,sc.getServiceComponentHosts().size());sch1.persist();sch2.persist();ServiceComponentHost schCheck=sc.getServiceComponentHost("h2");Assert.assertNotNull(schCheck);Assert.assertEquals("h2",schCheck.getHostName());ServiceComponentHost sch3=serviceComponentHostFactory.createNew(sc,"h3",false);sc.addServiceComponentHost(sch3);sch3.persist();Assert.assertNotNull(sc.getServiceComponentHost("h3"));sch1.setDesiredStackVersion(new StackId("HDP-1.1.0"));sch1.setState(State.STARTING);sch1.setStackVersion(new StackId("HDP-1.0.0"));sch1.setDesiredState(State.STARTED);HostComponentDesiredStateDAO desiredStateDAO=injector.getInstance(HostComponentDesiredStateDAO.class);HostComponentStateDAO liveStateDAO=injector.getInstance(HostComponentStateDAO.class);HostComponentDesiredStateEntityPK dPK=new HostComponentDesiredStateEntityPK();HostComponentStateEntityPK lPK=new HostComponentStateEntityPK();dPK.setClusterId(cluster.getClusterId());dPK.setComponentName(componentName);dPK.setHostName("h1");dPK.setServiceName(serviceName);lPK.setClusterId(cluster.getClusterId());lPK.setComponentName(componentName);lPK.setHostName("h1");lPK.setServiceName(serviceName);HostComponentDesiredStateEntity desiredStateEntity=desiredStateDAO.findByPK(dPK);HostComponentStateEntity stateEntity=liveStateDAO.findByPK(lPK);ServiceComponentHost sch=serviceComponentHostFactory.createExisting(sc,stateEntity,desiredStateEntity);Assert.assertNotNull(sch);Assert.assertEquals(State.STARTING,sch.getState());Assert.assertEquals(State.STARTED,sch.getDesiredState());Assert.assertEquals("HDP-1.0.0",sch.getStackVersion().getStackId());Assert.assertEquals("HDP-1.1.0",sch.getDesiredStackVersion().getStackId());}

}
