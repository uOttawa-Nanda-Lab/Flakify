@Test public void testUpdateResources() throws Exception {
  TestProviderModule providerModule=new TestProviderModule();
  TestResourceProvider resourceProvider=(TestResourceProvider)providerModule.getResourceProvider(Resource.Type.Host);
  ClusterController controller=new ClusterControllerImpl(providerModule);
  Map<String,Object> propertyMap=new HashMap<String,Object>();
  propertyMap.put(PropertyHelper.getPropertyId("c1","p1"),99);
  propertyMap.put(PropertyHelper.getPropertyId("c1","p2"),2);
  Request request=PropertyHelper.getUpdateRequest(propertyMap);
  Predicate predicate=new PredicateBuilder().property("c1/p2").equals(1).toPredicate();
  controller.updateResources(Resource.Type.Host,request,predicate);
  Assert.assertEquals(TestResourceProvider.Action.Update,resourceProvider.getLastAction());
  Assert.assertSame(request,resourceProvider.getLastRequest());
  Assert.assertSame(predicate,resourceProvider.getLastPredicate());
}
