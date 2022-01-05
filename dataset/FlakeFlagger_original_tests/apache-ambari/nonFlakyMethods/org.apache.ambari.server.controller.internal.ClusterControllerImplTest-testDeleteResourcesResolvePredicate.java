@Test public void testDeleteResourcesResolvePredicate() throws Exception {
  TestProviderModule providerModule=new TestProviderModule();
  TestResourceProvider resourceProvider=(TestResourceProvider)providerModule.getResourceProvider(Resource.Type.Host);
  ClusterController controller=new ClusterControllerImpl(providerModule);
  Predicate predicate=new PredicateBuilder().property("c3/p6").equals(1).toPredicate();
  controller.deleteResources(Resource.Type.Host,predicate);
  Assert.assertEquals(TestResourceProvider.Action.Delete,resourceProvider.getLastAction());
  Assert.assertNull(resourceProvider.getLastRequest());
  Predicate lastPredicate=resourceProvider.getLastPredicate();
  Assert.assertFalse(predicate.equals(lastPredicate));
  Set<String> predicatePropertyIds=PredicateHelper.getPropertyIds(lastPredicate);
  Collection<String> keyPropertyIds=resourceProvider.getKeyPropertyIds().values();
  Assert.assertEquals(predicatePropertyIds.size(),keyPropertyIds.size());
  Assert.assertTrue(keyPropertyIds.containsAll(predicatePropertyIds));
}
