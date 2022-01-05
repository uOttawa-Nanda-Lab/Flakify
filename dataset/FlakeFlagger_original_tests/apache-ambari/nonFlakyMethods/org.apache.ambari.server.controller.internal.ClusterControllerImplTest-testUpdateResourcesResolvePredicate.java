@Test public void testUpdateResourcesResolvePredicate() throws Exception {
  TestProviderModule providerModule=new TestProviderModule();
  TestResourceProvider resourceProvider=(TestResourceProvider)providerModule.getResourceProvider(Resource.Type.Host);
  ClusterController controller=new ClusterControllerImpl(providerModule);
  Map<String,Object> propertyMap=new HashMap<String,Object>();
  propertyMap.put(PropertyHelper.getPropertyId("c1","p1"),99);
  propertyMap.put(PropertyHelper.getPropertyId("c1","p2"),2);
  Request request=PropertyHelper.getUpdateRequest(propertyMap);
  Predicate predicate=new PredicateBuilder().property("c3/p6").equals(1).toPredicate();
  controller.updateResources(Resource.Type.Host,request,predicate);
  Assert.assertEquals(TestResourceProvider.Action.Update,resourceProvider.getLastAction());
  Assert.assertSame(request,resourceProvider.getLastRequest());
  Predicate lastPredicate=resourceProvider.getLastPredicate();
  Assert.assertFalse(predicate.equals(lastPredicate));
  Set<String> predicatePropertyIds=PredicateHelper.getPropertyIds(lastPredicate);
  Collection<String> keyPropertyIds=resourceProvider.getKeyPropertyIds().values();
  Assert.assertEquals(predicatePropertyIds.size(),keyPropertyIds.size());
  Assert.assertTrue(keyPropertyIds.containsAll(predicatePropertyIds));
}
