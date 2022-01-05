@Test public void testUpdateServiceResources() throws Exception {
  Resource.Type type=Resource.Type.Service;
  AmbariManagementController managementController=createMock(AmbariManagementController.class);
  RequestStatusResponse response=createNiceMock(RequestStatusResponse.class);
  expect(managementController.updateServices(anyObject(Set.class))).andReturn(response).once();
  replay(managementController,response);
  ResourceProvider provider=ResourceProviderImpl.getResourceProvider(type,PropertyHelper.getPropertyIds(type),PropertyHelper.getKeyPropertyIds(type),managementController);
  Map<String,Object> properties=new LinkedHashMap<String,Object>();
  properties.put(ServiceResourceProvider.SERVICE_SERVICE_STATE_PROPERTY_ID,"DEPLOYED");
  Request request=PropertyHelper.getUpdateRequest(properties);
  Predicate predicate=new PredicateBuilder().property(ServiceResourceProvider.SERVICE_CLUSTER_NAME_PROPERTY_ID).equals("Cluster100").and().property(ServiceResourceProvider.SERVICE_SERVICE_NAME_PROPERTY_ID).equals("Service102").toPredicate();
  provider.updateResources(request,predicate);
  verify(managementController,response);
}
