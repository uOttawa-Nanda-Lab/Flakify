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

  @Test
  public void testConvertToResponse() throws AmbariException {
    String serviceName = "HDFS";
    Service s = serviceFactory.createNew(cluster, serviceName);
    cluster.addService(s);
    Service service = cluster.getService(serviceName);
    Assert.assertNotNull(service);

    ServiceResponse r = s.convertToResponse();
    Assert.assertEquals(s.getName(), r.getServiceName());
    Assert.assertEquals(s.getCluster().getClusterName(),
        r.getClusterName());
    Assert.assertEquals(s.getDesiredStackVersion().getStackId(),
        r.getDesiredStackVersion());
    Assert.assertEquals(s.getDesiredState().toString(),
        r.getDesiredState());

    service.setDesiredStackVersion(new StackId("HDP-1.1.0"));
    service.setDesiredState(State.INSTALLING);
    r = s.convertToResponse();
    Assert.assertEquals(s.getName(), r.getServiceName());
    Assert.assertEquals(s.getCluster().getClusterName(),
        r.getClusterName());
    Assert.assertEquals(s.getDesiredStackVersion().getStackId(),
        r.getDesiredStackVersion());
    Assert.assertEquals(s.getDesiredState().toString(),
        r.getDesiredState());
    // FIXME add checks for configs

    StringBuilder sb = new StringBuilder();
    s.debugDump(sb);
    // TODO better checks?
    Assert.assertFalse(sb.toString().isEmpty());

  }

}
