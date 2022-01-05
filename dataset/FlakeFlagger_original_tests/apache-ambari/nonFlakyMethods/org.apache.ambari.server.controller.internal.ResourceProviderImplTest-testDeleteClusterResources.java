@Test public void testDeleteClusterResources() throws Exception {
  Resource.Type type=Resource.Type.Cluster;
  AmbariManagementController managementController=createMock(AmbariManagementController.class);
  RequestStatusResponse response=createNiceMock(RequestStatusResponse.class);
  Set<ClusterResponse> nameResponse=new HashSet<ClusterResponse>();
  nameResponse.add(new ClusterResponse(102L,"Cluster102",null,null));
  managementController.deleteCluster(Matchers.clusterRequest(null,"Cluster102",null,null));
  managementController.deleteCluster(Matchers.clusterRequest(103L,null,null,null));
  replay(managementController,response);
  ResourceProvider provider=ResourceProviderImpl.getResourceProvider(type,PropertyHelper.getPropertyIds(type),PropertyHelper.getKeyPropertyIds(type),managementController);
  TestObserver observer=new TestObserver();
  ((ObservableResourceProvider)provider).addObserver(observer);
  Predicate predicate=new PredicateBuilder().property(ClusterResourceProvider.CLUSTER_NAME_PROPERTY_ID).equals("Cluster102").toPredicate();
  provider.deleteResources(predicate);
  predicate=new PredicateBuilder().property(ClusterResourceProvider.CLUSTER_ID_PROPERTY_ID).equals(103L).toPredicate();
  provider.deleteResources(predicate);
  ResourceProviderEvent lastEvent=observer.getLastEvent();
  Assert.assertNotNull(lastEvent);
  Assert.assertEquals(Resource.Type.Cluster,lastEvent.getResourceType());
  Assert.assertEquals(ResourceProviderEvent.Type.Delete,lastEvent.getType());
  Assert.assertEquals(predicate,lastEvent.getPredicate());
  Assert.assertNull(lastEvent.getRequest());
  verify(managementController,response);
}
