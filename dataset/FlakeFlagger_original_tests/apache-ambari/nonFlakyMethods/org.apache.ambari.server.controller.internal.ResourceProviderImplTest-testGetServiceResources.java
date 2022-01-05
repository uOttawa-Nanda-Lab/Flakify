@Test public void testGetServiceResources() throws Exception {
  Resource.Type type=Resource.Type.Service;
  AmbariManagementController managementController=createMock(AmbariManagementController.class);
  Set<ServiceResponse> allResponse=new HashSet<ServiceResponse>();
  allResponse.add(new ServiceResponse(100L,"Cluster100","Service100",null,"HDP-0.1",null));
  allResponse.add(new ServiceResponse(100L,"Cluster100","Service101",null,"HDP-0.1",null));
  allResponse.add(new ServiceResponse(100L,"Cluster100","Service102",null,"HDP-0.1",null));
  allResponse.add(new ServiceResponse(100L,"Cluster100","Service103",null,"HDP-0.1",null));
  allResponse.add(new ServiceResponse(100L,"Cluster100","Service104",null,"HDP-0.1",null));
  Set<ServiceResponse> nameResponse=new HashSet<ServiceResponse>();
  nameResponse.add(new ServiceResponse(100L,"Cluster100","Service102",null,"HDP-0.1",null));
  Set<ServiceResponse> stateResponse=new HashSet<ServiceResponse>();
  stateResponse.add(new ServiceResponse(100L,"Cluster100","Service100",null,"HDP-0.1",null));
  stateResponse.add(new ServiceResponse(100L,"Cluster100","Service102",null,"HDP-0.1",null));
  stateResponse.add(new ServiceResponse(100L,"Cluster100","Service104",null,"HDP-0.1",null));
  expect(managementController.getServices(anyObject(Set.class))).andReturn(allResponse).once();
  expect(managementController.getServices(anyObject(Set.class))).andReturn(nameResponse).once();
  expect(managementController.getServices(anyObject(Set.class))).andReturn(stateResponse).once();
  replay(managementController);
  ResourceProvider provider=ResourceProviderImpl.getResourceProvider(type,PropertyHelper.getPropertyIds(type),PropertyHelper.getKeyPropertyIds(type),managementController);
  Set<String> propertyIds=new HashSet<String>();
  propertyIds.add(ServiceResourceProvider.SERVICE_CLUSTER_NAME_PROPERTY_ID);
  propertyIds.add(ServiceResourceProvider.SERVICE_SERVICE_NAME_PROPERTY_ID);
  Request request=PropertyHelper.getReadRequest(propertyIds);
  Set<Resource> resources=provider.getResources(request,null);
  Assert.assertEquals(5,resources.size());
  Set<String> names=new HashSet<String>();
  for (  Resource resource : resources) {
    String clusterName=(String)resource.getPropertyValue(ServiceResourceProvider.SERVICE_CLUSTER_NAME_PROPERTY_ID);
    Assert.assertEquals("Cluster100",clusterName);
    names.add((String)resource.getPropertyValue(ServiceResourceProvider.SERVICE_SERVICE_NAME_PROPERTY_ID));
  }
  for (  ServiceResponse serviceResponse : allResponse) {
    Assert.assertTrue(names.contains(serviceResponse.getServiceName()));
  }
  Predicate predicate=new PredicateBuilder().property(ServiceResourceProvider.SERVICE_SERVICE_NAME_PROPERTY_ID).equals("Service102").toPredicate();
  resources=provider.getResources(request,predicate);
  Assert.assertEquals(1,resources.size());
  Assert.assertEquals("Cluster100",resources.iterator().next().getPropertyValue(ServiceResourceProvider.SERVICE_CLUSTER_NAME_PROPERTY_ID));
  Assert.assertEquals("Service102",resources.iterator().next().getPropertyValue(ServiceResourceProvider.SERVICE_SERVICE_NAME_PROPERTY_ID));
  predicate=new PredicateBuilder().property(ServiceResourceProvider.SERVICE_SERVICE_STATE_PROPERTY_ID).equals("DEPLOYED").toPredicate();
  resources=provider.getResources(request,predicate);
  Assert.assertEquals(3,resources.size());
  names=new HashSet<String>();
  for (  Resource resource : resources) {
    String clusterName=(String)resource.getPropertyValue(ServiceResourceProvider.SERVICE_CLUSTER_NAME_PROPERTY_ID);
    Assert.assertEquals("Cluster100",clusterName);
    names.add((String)resource.getPropertyValue(ServiceResourceProvider.SERVICE_SERVICE_NAME_PROPERTY_ID));
  }
  for (  ServiceResponse serviceResponse : stateResponse) {
    Assert.assertTrue(names.contains(serviceResponse.getServiceName()));
  }
  verify(managementController);
}
