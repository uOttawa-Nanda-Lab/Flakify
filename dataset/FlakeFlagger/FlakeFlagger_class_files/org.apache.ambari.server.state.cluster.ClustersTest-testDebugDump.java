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

package org.apache.ambari.server.state.cluster;

import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import junit.framework.Assert;

import org.apache.ambari.server.AmbariException;
import org.apache.ambari.server.ClusterNotFoundException;
import org.apache.ambari.server.HostNotFoundException;
import org.apache.ambari.server.api.services.AmbariMetaInfo;
import org.apache.ambari.server.orm.GuiceJpaInitializer;
import org.apache.ambari.server.orm.InMemoryDefaultTestModule;
import org.apache.ambari.server.state.Cluster;
import org.apache.ambari.server.state.Clusters;
import org.apache.ambari.server.state.Host;
import org.apache.ambari.server.state.StackId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClustersTest {

  private Clusters clusters;
  private Injector injector;
  @Inject
  private AmbariMetaInfo metaInfo;

  @Before
  public void setup() throws Exception {
    injector = Guice.createInjector(new InMemoryDefaultTestModule());
    injector.getInstance(GuiceJpaInitializer.class);
    clusters = injector.getInstance(Clusters.class);
    injector.injectMembers(this);
    metaInfo.init();
  }

  @Test public void testDebugDump() throws AmbariException{String c1="c1";String c2="c2";String h1="h1";String h2="h2";String h3="h3";clusters.addCluster(c1);clusters.addCluster(c2);clusters.getCluster(c1).setDesiredStackVersion(new StackId("HDP-0.1"));clusters.getCluster(c2).setDesiredStackVersion(new StackId("HDP-0.1"));clusters.addHost(h1);clusters.addHost(h2);clusters.addHost(h3);clusters.getHost(h1).setOsType("redhat6");clusters.getHost(h2).setOsType("centos5");clusters.getHost(h3).setOsType("centos6");clusters.getHost(h1).persist();clusters.getHost(h2).persist();clusters.getHost(h3).persist();clusters.mapHostToCluster(h1,c1);clusters.mapHostToCluster(h2,c1);StringBuilder sb=new StringBuilder();clusters.debugDump(sb);}

}
