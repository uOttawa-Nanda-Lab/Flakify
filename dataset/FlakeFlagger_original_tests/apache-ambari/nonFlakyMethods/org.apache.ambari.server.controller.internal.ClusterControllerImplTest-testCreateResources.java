@Test public void testCreateResources() throws Exception {
  TestProviderModule providerModule=new TestProviderModule();
  TestResourceProvider resourceProvider=(TestResourceProvider)providerModule.getResourceProvider(Resource.Type.Host);
  ClusterController controller=new ClusterControllerImpl(providerModule);
  Set<Map<String,Object>> properties=new HashSet<Map<String,Object>>();
  Map<String,Object> propertyMap=new HashMap<String,Object>();
  propertyMap.put(PropertyHelper.getPropertyId("c1","p1"),99);
  propertyMap.put(PropertyHelper.getPropertyId("c1","p2"),2);
  properties.add(propertyMap);
  Request request=PropertyHelper.getCreateRequest(properties);
  controller.createResources(Resource.Type.Host,request);
  Assert.assertEquals(TestResourceProvider.Action.Create,resourceProvider.getLastAction());
  Assert.assertSame(request,resourceProvider.getLastRequest());
  Assert.assertNull(resourceProvider.getLastPredicate());
}
