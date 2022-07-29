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

  @Test public void testConvertToResponse() throws AmbariException{String componentName="NAMENODE";ServiceComponent component=serviceComponentFactory.createNew(service,componentName);service.addServiceComponent(component);component.persist();ServiceComponent sc=service.getServiceComponent(componentName);Assert.assertNotNull(sc);sc.setDesiredState(State.INSTALLED);sc.setDesiredStackVersion(new StackId("HDP-1.0.0"));ServiceComponentResponse r=sc.convertToResponse();Assert.assertEquals(sc.getClusterName(),r.getClusterName());Assert.assertEquals(sc.getClusterId(),r.getClusterId().longValue());Assert.assertEquals(sc.getName(),r.getComponentName());Assert.assertEquals(sc.getServiceName(),r.getServiceName());Assert.assertEquals(sc.getDesiredStackVersion().getStackId(),r.getDesiredStackVersion());Assert.assertEquals(sc.getDesiredState().toString(),r.getDesiredState());StringBuilder sb=new StringBuilder();sc.debugDump(sb);Assert.assertFalse(sb.toString().isEmpty());}

}
