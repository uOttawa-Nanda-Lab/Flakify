@Test public void testCreateClusterResources() throws Exception {
  Resource.Type type=Resource.Type.Cluster;
  AmbariManagementController managementController=createMock(AmbariManagementController.class);
  RequestStatusResponse response=createNiceMock(RequestStatusResponse.class);
  managementController.createCluster(Matchers.clusterRequest(null,"Cluster100","HDP-0.1",null));
  managementController.createCluster(Matchers.clusterRequest(99L,null,"HDP-0.1",null));
  replay(managementController,response);
  ResourceProvider provider=ResourceProviderImpl.getResourceProvider(type,PropertyHelper.getPropertyIds(type),PropertyHelper.getKeyPropertyIds(type),managementController);
  TestObserver observer=new TestObserver();
  ((ObservableResourceProvider)provider).addObserver(observer);
  Set<Map<String,Object>> propertySet=new LinkedHashSet<Map<String,Object>>();
  Map<String,Object> properties=new LinkedHashMap<String,Object>();
  properties.put(ClusterResourceProvider.CLUSTER_NAME_PROPERTY_ID,"Cluster100");
  properties.put(ClusterResourceProvider.CLUSTER_VERSION_PROPERTY_ID,"HDP-0.1");
  propertySet.add(properties);
  properties=new LinkedHashMap<String,Object>();
  properties.put(ClusterResourceProvider.CLUSTER_ID_PROPERTY_ID,99L);
  properties.put(ClusterResourceProvider.CLUSTER_VERSION_PROPERTY_ID,"HDP-0.1");
  propertySet.add(properties);
  Request request=PropertyHelper.getCreateRequest(propertySet);
  provider.createResources(request);
  ResourceProviderEvent lastEvent=observer.getLastEvent();
  Assert.assertNotNull(lastEvent);
  Assert.assertEquals(Resource.Type.Cluster,lastEvent.getResourceType());
  Assert.assertEquals(ResourceProviderEvent.Type.Create,lastEvent.getType());
  Assert.assertEquals(request,lastEvent.getRequest());
  Assert.assertNull(lastEvent.getPredicate());
  verify(managementController,response);
}
