@Test public void testCreateServiceResources() throws Exception {
  Resource.Type type=Resource.Type.Service;
  AmbariManagementController managementController=createMock(AmbariManagementController.class);
  RequestStatusResponse response=createNiceMock(RequestStatusResponse.class);
  managementController.createServices(anyObject(Set.class));
  replay(managementController,response);
  ResourceProvider provider=ResourceProviderImpl.getResourceProvider(type,PropertyHelper.getPropertyIds(type),PropertyHelper.getKeyPropertyIds(type),managementController);
  Set<Map<String,Object>> propertySet=new LinkedHashSet<Map<String,Object>>();
  Map<String,Object> properties=new LinkedHashMap<String,Object>();
  properties.put(ServiceResourceProvider.SERVICE_CLUSTER_NAME_PROPERTY_ID,"Cluster100");
  properties.put(ServiceResourceProvider.SERVICE_SERVICE_NAME_PROPERTY_ID,"Service100");
  properties.put(ServiceResourceProvider.SERVICE_SERVICE_STATE_PROPERTY_ID,"DEPLOYED");
  propertySet.add(properties);
  Request request=PropertyHelper.getCreateRequest(propertySet);
  provider.createResources(request);
  verify(managementController,response);
}
