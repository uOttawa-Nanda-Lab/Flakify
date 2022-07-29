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

package org.apache.ambari.server.controller.internal;

import org.apache.ambari.server.controller.*;
import org.apache.ambari.server.controller.spi.UnsupportedPropertyException;
import org.apache.ambari.server.controller.utilities.PredicateBuilder;
import org.apache.ambari.server.controller.utilities.PropertyHelper;
import org.apache.ambari.server.controller.RequestStatusResponse;
import org.apache.ambari.server.controller.spi.Predicate;
import org.apache.ambari.server.controller.spi.Request;
import org.apache.ambari.server.controller.spi.Resource;
import org.apache.ambari.server.controller.spi.ResourceProvider;
import org.easymock.EasyMock;
import org.easymock.IArgumentMatcher;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Resource provider tests.
 */
public class ResourceProviderImplTest {

  @Test public void testUpdateClusterResources() throws Exception{Resource.Type type=Resource.Type.Cluster;AmbariManagementController managementController=createMock(AmbariManagementController.class);RequestStatusResponse response=createNiceMock(RequestStatusResponse.class);Set<ClusterResponse> nameResponse=new HashSet<ClusterResponse>();nameResponse.add(new ClusterResponse(102L,"Cluster102",null,null));expect(managementController.getClusters(anyObject(Set.class))).andReturn(nameResponse).once();expect(managementController.updateCluster(Matchers.clusterRequest(102L,"Cluster102","HDP-0.1",null))).andReturn(response).once();expect(managementController.updateCluster(Matchers.clusterRequest(103L,null,"HDP-0.1",null))).andReturn(response).once();replay(managementController,response);ResourceProvider provider=ResourceProviderImpl.getResourceProvider(type,PropertyHelper.getPropertyIds(type),PropertyHelper.getKeyPropertyIds(type),managementController);TestObserver observer=new TestObserver();((ObservableResourceProvider)provider).addObserver(observer);Map<String, Object> properties=new LinkedHashMap<String, Object>();properties.put(ClusterResourceProvider.CLUSTER_VERSION_PROPERTY_ID,"HDP-0.1");Request request=PropertyHelper.getUpdateRequest(properties);Predicate predicate=new PredicateBuilder().property(ClusterResourceProvider.CLUSTER_NAME_PROPERTY_ID).equals("Cluster102").toPredicate();provider.updateResources(request,predicate);predicate=new PredicateBuilder().property(ClusterResourceProvider.CLUSTER_ID_PROPERTY_ID).equals(103L).toPredicate();provider.updateResources(request,predicate);ResourceProviderEvent lastEvent=observer.getLastEvent();Assert.assertNotNull(lastEvent);Assert.assertEquals(Resource.Type.Cluster,lastEvent.getResourceType());Assert.assertEquals(ResourceProviderEvent.Type.Update,lastEvent.getType());Assert.assertEquals(request,lastEvent.getRequest());Assert.assertEquals(predicate,lastEvent.getPredicate());verify(managementController,response);}

  


  // ----- helper methods ----------------------------------------------------

  public static class Matchers
  {
    public static ClusterRequest clusterRequest(Long clusterId, String clusterName, String stackVersion, Set<String> hostNames)
    {
      EasyMock.reportMatcher(new ClusterRequestMatcher(clusterId, clusterName, stackVersion, hostNames));
      return null;
    }

    public static ServiceRequest serviceRequest(String clusterName, String serviceName, Map<String, String> configVersions, String desiredState)
    {
      EasyMock.reportMatcher(new ServiceRequestMatcher(clusterName, serviceName, configVersions, desiredState));
      return null;
    }
  }

  


  // ----- innner classes ----------------------------------------------------

  public static class ClusterRequestMatcher extends ClusterRequest implements IArgumentMatcher {

    public ClusterRequestMatcher(Long clusterId, String clusterName, String stackVersion, Set<String> hostNames) {
      super(clusterId, clusterName, stackVersion, hostNames);
    }

    @Override
    public boolean matches(Object o) {
      return o instanceof ClusterRequest &&
          eq(((ClusterRequest) o).getClusterId(), getClusterId()) &&
          eq(((ClusterRequest) o).getClusterName(), getClusterName()) &&
          eq(((ClusterRequest) o).getStackVersion(), getStackVersion()) &&
          eq(((ClusterRequest) o).getHostNames(), getHostNames());
    }

    @Override
    public void appendTo(StringBuffer stringBuffer) {
      stringBuffer.append("ClusterRequestMatcher(" + "" + ")");
    }
  }

  public static class ServiceRequestMatcher extends ServiceRequest implements IArgumentMatcher {

    public ServiceRequestMatcher(String clusterName, String serviceName, Map<String, String> configVersions, String desiredState) {
      super(clusterName, serviceName, configVersions, desiredState);
    }

    @Override
    public boolean matches(Object o) {
      return o instanceof ServiceRequest &&
          eq(((ServiceRequest) o).getClusterName(), getClusterName()) &&
          eq(((ServiceRequest) o).getServiceName(), getServiceName()) &&
          eq(((ServiceRequest) o).getConfigVersions(), getConfigVersions()) &&
          eq(((ServiceRequest) o).getDesiredState(), getDesiredState());

    }

    @Override
    public void appendTo(StringBuffer stringBuffer) {
      stringBuffer.append("ClusterRequestMatcher(" + "" + ")");
    }
  }

  public class TestObserver implements ResourceProviderObserver {

    ResourceProviderEvent lastEvent = null;

    @Override
    public void update(ResourceProviderEvent event) {
      lastEvent = event;
    }

    public ResourceProviderEvent getLastEvent() {
      return lastEvent;
    }
  }

}
