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

import junit.framework.Assert;
import org.apache.ambari.server.AmbariException;
import org.apache.ambari.server.controller.spi.ProviderModule;
import org.apache.ambari.server.controller.spi.RequestStatus;
import org.apache.ambari.server.controller.spi.Schema;
import org.apache.ambari.server.controller.spi.UnsupportedPropertyException;
import org.apache.ambari.server.controller.utilities.PredicateHelper;
import org.apache.ambari.server.controller.utilities.PropertyHelper;
import org.apache.ambari.server.controller.spi.ClusterController;
import org.apache.ambari.server.controller.spi.Predicate;
import org.apache.ambari.server.controller.spi.PropertyProvider;
import org.apache.ambari.server.controller.spi.Request;
import org.apache.ambari.server.controller.spi.Resource;
import org.apache.ambari.server.controller.spi.ResourceProvider;
import org.apache.ambari.server.controller.utilities.PredicateBuilder;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Cluster controller tests
 */
public class ClusterControllerImplTest {

  private static final Set<String> propertyProviderProperties = new HashSet<String>();

  static {
    propertyProviderProperties.add(PropertyHelper.getPropertyId("c3", "p5"));
    propertyProviderProperties.add(PropertyHelper.getPropertyId("c3", "p6"));
    propertyProviderProperties.add(PropertyHelper.getPropertyId("c4", "p7"));
    propertyProviderProperties.add(PropertyHelper.getPropertyId("c4", "p8"));
  }

  private static final PropertyProvider propertyProvider = new PropertyProvider() {
    @Override
    public Set<Resource> populateResources(Set<Resource> resources, Request request, Predicate predicate) {

      int cnt = 0;
      for (Resource resource : resources){
        resource.setProperty(PropertyHelper.getPropertyId("c3", "p5"), cnt + 100);
        resource.setProperty(PropertyHelper.getPropertyId("c3", "p6"), cnt % 2);
        resource.setProperty(PropertyHelper.getPropertyId("c4", "p7"), "monkey");
        resource.setProperty(PropertyHelper.getPropertyId("c4", "p8"), "runner");
        ++cnt;
      }
      return resources;
    }

    @Override
    public Set<String> getPropertyIds() {
      return propertyProviderProperties;
    }
  };

  private static final List<PropertyProvider> propertyProviders = new LinkedList<PropertyProvider>();

  static {
    propertyProviders.add(propertyProvider);
  }

  private static final Map<Resource.Type, String> keyPropertyIds = new HashMap<Resource.Type, String>();

  static {
    keyPropertyIds.put(Resource.Type.Cluster, PropertyHelper.getPropertyId("c1", "p1"));
    keyPropertyIds.put(Resource.Type.Host, PropertyHelper.getPropertyId("c1", "p2"));
  }

  private static final Set<String> resourceProviderProperties = new HashSet<String>();

  static {
    resourceProviderProperties.add(PropertyHelper.getPropertyId("c1", "p1"));
    resourceProviderProperties.add(PropertyHelper.getPropertyId("c1", "p2"));
    resourceProviderProperties.add(PropertyHelper.getPropertyId("c1", "p3"));
    resourceProviderProperties.add(PropertyHelper.getPropertyId("c2", "p4"));
  }

  @Test public void testUpdateResourcesResolvePredicate() throws Exception{TestProviderModule providerModule=new TestProviderModule();TestResourceProvider resourceProvider=(TestResourceProvider)providerModule.getResourceProvider(Resource.Type.Host);ClusterController controller=new ClusterControllerImpl(providerModule);Map<String, Object> propertyMap=new HashMap<String, Object>();propertyMap.put(PropertyHelper.getPropertyId("c1","p1"),99);propertyMap.put(PropertyHelper.getPropertyId("c1","p2"),2);Request request=PropertyHelper.getUpdateRequest(propertyMap);Predicate predicate=new PredicateBuilder().property("c3/p6").equals(1).toPredicate();controller.updateResources(Resource.Type.Host,request,predicate);Assert.assertEquals(TestResourceProvider.Action.Update,resourceProvider.getLastAction());Assert.assertSame(request,resourceProvider.getLastRequest());Predicate lastPredicate=resourceProvider.getLastPredicate();Assert.assertFalse(predicate.equals(lastPredicate));Set<String> predicatePropertyIds=PredicateHelper.getPropertyIds(lastPredicate);Collection<String> keyPropertyIds=resourceProvider.getKeyPropertyIds().values();Assert.assertEquals(predicatePropertyIds.size(),keyPropertyIds.size());Assert.assertTrue(keyPropertyIds.containsAll(predicatePropertyIds));}

  private static class TestProviderModule implements ProviderModule {
    private Map<Resource.Type, ResourceProvider> providers = new HashMap<Resource.Type, ResourceProvider>();

    private TestProviderModule() {
      providers.put(Resource.Type.Cluster, new TestResourceProvider());
      providers.put(Resource.Type.Service, new TestResourceProvider());
      providers.put(Resource.Type.Component, new TestResourceProvider());
      providers.put(Resource.Type.Host, new TestResourceProvider());
      providers.put(Resource.Type.HostComponent, new TestResourceProvider());
    }

    @Override
    public ResourceProvider getResourceProvider(Resource.Type type) {
      return providers.get(type);
    }

    @Override
    public List<PropertyProvider> getPropertyProviders(Resource.Type type) {
      return propertyProviders;
    }
  }

  private static class TestResourceProvider implements ResourceProvider {
    private Action lastAction = null;
    private Request lastRequest = null;
    private Predicate lastPredicate = null;

    @Override
    public Set<Resource> getResources(Request request, Predicate predicate) throws AmbariException, UnsupportedPropertyException {

      Set<Resource> resources = new HashSet<Resource>();

      for (int cnt = 0; cnt < 4; ++ cnt) {
        ResourceImpl resource = new ResourceImpl(Resource.Type.Host);

        resource.setProperty(PropertyHelper.getPropertyId("c1", "p1"), cnt);
        resource.setProperty(PropertyHelper.getPropertyId("c1", "p2"), cnt % 2);
        resource.setProperty(PropertyHelper.getPropertyId("c1", "p3"), "foo");
        resource.setProperty(PropertyHelper.getPropertyId("c2", "p4"), "bar");
        resources.add(resource);
      }

      return resources;
    }

    @Override
    public RequestStatus createResources(Request request) throws AmbariException, UnsupportedPropertyException {
      lastAction = Action.Create;
      lastRequest = request;
      lastPredicate = null;
      return new RequestStatusImpl(null);
    }

    @Override
    public RequestStatus updateResources(Request request, Predicate predicate) throws AmbariException, UnsupportedPropertyException {
      lastAction = Action.Update;
      lastRequest = request;
      lastPredicate = predicate;
      return new RequestStatusImpl(null);
    }

    @Override
    public RequestStatus deleteResources(Predicate predicate) throws AmbariException, UnsupportedPropertyException {
      lastAction = Action.Delete;
      lastRequest = null;
      lastPredicate = predicate;
      return new RequestStatusImpl(null);
    }

    @Override
    public Set<String> getPropertyIds() {
      return resourceProviderProperties;
    }

    @Override
    public Map<Resource.Type, String> getKeyPropertyIds() {
      return keyPropertyIds;
    }

    public Action getLastAction() {
      return lastAction;
    }

    public Request getLastRequest() {
      return lastRequest;
    }

    public Predicate getLastPredicate() {
      return lastPredicate;
    }

    public enum Action {
      Create,
      Update,
      Delete
    }

  }

}


