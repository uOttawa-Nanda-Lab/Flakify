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

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;

import junit.framework.Assert;

import org.apache.ambari.server.AmbariException;
import org.apache.ambari.server.api.services.AmbariMetaInfo;
import org.apache.ambari.server.controller.ServiceResponse;
import org.apache.ambari.server.orm.GuiceJpaInitializer;
import org.apache.ambari.server.orm.InMemoryDefaultTestModule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ServiceTest {

  private Clusters clusters;
  private Cluster cluster;
  private String clusterName;
  private Injector injector;
  private ServiceFactory serviceFactory;
  private ServiceComponentFactory serviceComponentFactory;
  private AmbariMetaInfo metaInfo;

  @Before
  public void setup() throws Exception {
    injector = Guice.createInjector(new InMemoryDefaultTestModule());
    injector.getInstance(GuiceJpaInitializer.class);
    clusters = injector.getInstance(Clusters.class);
    serviceFactory = injector.getInstance(ServiceFactory.class);
    serviceComponentFactory = injector.getInstance(
        ServiceComponentFactory.class);
    metaInfo = injector.getInstance(AmbariMetaInfo.class);
    metaInfo.init();
    clusterName = "foo";
    clusters.addCluster(clusterName);
    cluster = clusters.getCluster(clusterName);
    cluster.setDesiredStackVersion(new StackId("HDP-0.1"));
    Assert.assertNotNull(cluster);
  }

  @Test public void testAddAndGetServiceComponents() throws AmbariException{String serviceName="HDFS";Service s=serviceFactory.createNew(cluster,serviceName);cluster.addService(s);s.persist();Service service=cluster.getService(serviceName);Assert.assertNotNull(service);Assert.assertTrue(s.getServiceComponents().isEmpty());ServiceComponent sc1=serviceComponentFactory.createNew(s,"NAMENODE");ServiceComponent sc2=serviceComponentFactory.createNew(s,"DATANODE1");ServiceComponent sc3=serviceComponentFactory.createNew(s,"DATANODE2");Map<String, ServiceComponent> comps=new HashMap<String, ServiceComponent>();comps.put(sc1.getName(),sc1);comps.put(sc2.getName(),sc2);s.addServiceComponents(comps);Assert.assertEquals(2,s.getServiceComponents().size());Assert.assertNotNull(s.getServiceComponent(sc1.getName()));Assert.assertNotNull(s.getServiceComponent(sc2.getName()));try {s.getServiceComponent(sc3.getName());fail("Expected error when looking for invalid component");} catch (Exception e){}s.addServiceComponent(sc3);sc1.persist();sc2.persist();sc3.persist();ServiceComponent sc4=s.addServiceComponent("HDFS_CLIENT");Assert.assertNotNull(s.getServiceComponent(sc4.getName()));Assert.assertEquals(State.INIT,s.getServiceComponent("HDFS_CLIENT").getDesiredState());Assert.assertTrue(sc4.isClientComponent());sc4.persist();Assert.assertEquals(4,s.getServiceComponents().size());Assert.assertNotNull(s.getServiceComponent(sc3.getName()));Assert.assertEquals(sc3.getName(),s.getServiceComponent(sc3.getName()).getName());Assert.assertEquals(s.getName(),s.getServiceComponent(sc3.getName()).getServiceName());Assert.assertEquals(cluster.getClusterName(),s.getServiceComponent(sc3.getName()).getClusterName());sc4.setDesiredState(State.INSTALLING);Assert.assertEquals(State.INSTALLING,s.getServiceComponent("HDFS_CLIENT").getDesiredState());}

}
