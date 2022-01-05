@Test public void testDeleteResources() throws Exception {
  TestProviderModule providerModule=new TestProviderModule();
  TestResourceProvider resourceProvider=(TestResourceProvider)providerModule.getResourceProvider(Resource.Type.Host);
  ClusterController controller=new ClusterControllerImpl(providerModule);
  Predicate predicate=new PredicateBuilder().property("c1/p2").equals(1).toPredicate();
  controller.deleteResources(Resource.Type.Host,predicate);
  Assert.assertEquals(TestResourceProvider.Action.Delete,resourceProvider.getLastAction());
  Assert.assertNull(resourceProvider.getLastRequest());
  Assert.assertSame(predicate,resourceProvider.getLastPredicate());
}
