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

  @Test public void testAddAndGetCluster() throws AmbariException{String c1="foo";String c2="foo";clusters.addCluster(c1);try {clusters.addCluster(c1);fail("Exception should be thrown on invalid add");} catch (AmbariException e){}try {clusters.addCluster(c2);fail("Exception should be thrown on invalid add");} catch (AmbariException e){}c2="foo2";clusters.addCluster(c2);Assert.assertNotNull(clusters.getCluster(c1));Assert.assertNotNull(clusters.getCluster(c2));Assert.assertEquals(c1,clusters.getCluster(c1).getClusterName());Assert.assertEquals(c2,clusters.getCluster(c2).getClusterName());Map<String, Cluster> verifyClusters=clusters.getClusters();Assert.assertTrue(verifyClusters.containsKey(c1));Assert.assertTrue(verifyClusters.containsKey(c2));Assert.assertNotNull(verifyClusters.get(c1));Assert.assertNotNull(verifyClusters.get(c2));Cluster c=clusters.getCluster(c1);c.setClusterName("foobar");long cId=c.getClusterId();Cluster changed=clusters.getCluster("foobar");Assert.assertNotNull(changed);Assert.assertEquals(cId,changed.getClusterId());Assert.assertEquals("foobar",clusters.getClusterById(cId).getClusterName());}

}
