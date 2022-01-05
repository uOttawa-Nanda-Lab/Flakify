@Test public void testUpdateClusterResources() throws Exception {
  Resource.Type type=Resource.Type.Cluster;
  AmbariManagementController managementController=createMock(AmbariManagementController.class);
  RequestStatusResponse response=createNiceMock(RequestStatusResponse.class);
  Set<ClusterResponse> nameResponse=new HashSet<ClusterResponse>();
  nameResponse.add(new ClusterResponse(102L,"Cluster102",null,null));
  expect(managementController.getClusters(anyObject(Set.class))).andReturn(nameResponse).once();
  expect(managementController.updateCluster(Matchers.clusterRequest(102L,"Cluster102","HDP-0.1",null))).andReturn(response).once();
  expect(managementController.updateCluster(Matchers.clusterRequest(103L,null,"HDP-0.1",null))).andReturn(response).once();
  replay(managementController,response);
  ResourceProvider provider=ResourceProviderImpl.getResourceProvider(type,PropertyHelper.getPropertyIds(type),PropertyHelper.getKeyPropertyIds(type),managementController);
  TestObserver observer=new TestObserver();
  ((ObservableResourceProvider)provider).addObserver(observer);
  Map<String,Object> properties=new LinkedHashMap<String,Object>();
  properties.put(ClusterResourceProvider.CLUSTER_VERSION_PROPERTY_ID,"HDP-0.1");
  Request request=PropertyHelper.getUpdateRequest(properties);
  Predicate predicate=new PredicateBuilder().property(ClusterResourceProvider.CLUSTER_NAME_PROPERTY_ID).equals("Cluster102").toPredicate();
  provider.updateResources(request,predicate);
  predicate=new PredicateBuilder().property(ClusterResourceProvider.CLUSTER_ID_PROPERTY_ID).equals(103L).toPredicate();
  provider.updateResources(request,predicate);
  ResourceProviderEvent lastEvent=observer.getLastEvent();
  Assert.assertNotNull(lastEvent);
  Assert.assertEquals(Resource.Type.Cluster,lastEvent.getResourceType());
  Assert.assertEquals(ResourceProviderEvent.Type.Update,lastEvent.getType());
  Assert.assertEquals(request,lastEvent.getRequest());
  Assert.assertEquals(predicate,lastEvent.getPredicate());
  verify(managementController,response);
}
