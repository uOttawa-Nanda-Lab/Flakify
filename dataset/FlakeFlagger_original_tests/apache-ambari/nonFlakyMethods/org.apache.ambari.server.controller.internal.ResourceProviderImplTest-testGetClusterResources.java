@Test public void testGetClusterResources() throws Exception {
  Resource.Type type=Resource.Type.Cluster;
  AmbariManagementController managementController=createMock(AmbariManagementController.class);
  Set<ClusterResponse> allResponse=new HashSet<ClusterResponse>();
  allResponse.add(new ClusterResponse(100L,"Cluster100",null,null));
  allResponse.add(new ClusterResponse(101L,"Cluster101",null,null));
  allResponse.add(new ClusterResponse(102L,"Cluster102",null,null));
  allResponse.add(new ClusterResponse(103L,"Cluster103",null,null));
  allResponse.add(new ClusterResponse(104L,"Cluster104",null,null));
  Set<ClusterResponse> nameResponse=new HashSet<ClusterResponse>();
  nameResponse.add(new ClusterResponse(102L,"Cluster102",null,null));
  Set<ClusterResponse> idResponse=new HashSet<ClusterResponse>();
  idResponse.add(new ClusterResponse(103L,"Cluster103",null,null));
  expect(managementController.getClusters(anyObject(Set.class))).andReturn(allResponse).once();
  expect(managementController.getClusters(anyObject(Set.class))).andReturn(nameResponse).once();
  expect(managementController.getClusters(anyObject(Set.class))).andReturn(idResponse).once();
  replay(managementController);
  ResourceProvider provider=ResourceProviderImpl.getResourceProvider(type,PropertyHelper.getPropertyIds(type),PropertyHelper.getKeyPropertyIds(type),managementController);
  Set<String> propertyIds=new HashSet<String>();
  propertyIds.add(ClusterResourceProvider.CLUSTER_ID_PROPERTY_ID);
  propertyIds.add(ClusterResourceProvider.CLUSTER_NAME_PROPERTY_ID);
  Request request=PropertyHelper.getReadRequest(propertyIds);
  Set<Resource> resources=provider.getResources(request,null);
  Assert.assertEquals(5,resources.size());
  for (  Resource resource : resources) {
    Long id=(Long)resource.getPropertyValue(ClusterResourceProvider.CLUSTER_ID_PROPERTY_ID);
    String name=(String)resource.getPropertyValue(ClusterResourceProvider.CLUSTER_NAME_PROPERTY_ID);
    Assert.assertEquals(name,"Cluster" + id);
  }
  Predicate predicate=new PredicateBuilder().property(ClusterResourceProvider.CLUSTER_NAME_PROPERTY_ID).equals("Cluster102").toPredicate();
  resources=provider.getResources(request,predicate);
  Assert.assertEquals(1,resources.size());
  Assert.assertEquals(102L,resources.iterator().next().getPropertyValue(ClusterResourceProvider.CLUSTER_ID_PROPERTY_ID));
  Assert.assertEquals("Cluster102",resources.iterator().next().getPropertyValue(ClusterResourceProvider.CLUSTER_NAME_PROPERTY_ID));
  predicate=new PredicateBuilder().property(ClusterResourceProvider.CLUSTER_ID_PROPERTY_ID).equals(103L).toPredicate();
  resources=provider.getResources(request,predicate);
  Assert.assertEquals(1,resources.size());
  Assert.assertEquals(103L,resources.iterator().next().getPropertyValue(ClusterResourceProvider.CLUSTER_ID_PROPERTY_ID));
  Assert.assertEquals("Cluster103",resources.iterator().next().getPropertyValue(ClusterResourceProvider.CLUSTER_NAME_PROPERTY_ID));
  verify(managementController);
}
