@Test public void testGetResourcesWithPredicate() throws Exception {
  ClusterController controller=new ClusterControllerImpl(new TestProviderModule());
  Set<String> propertyIds=new HashSet<String>();
  propertyIds.add(PropertyHelper.getPropertyId("c1","p1"));
  propertyIds.add(PropertyHelper.getPropertyId("c1","p2"));
  propertyIds.add(PropertyHelper.getPropertyId("c1","p3"));
  propertyIds.add(PropertyHelper.getPropertyId("c2","p4"));
  Request request=PropertyHelper.getReadRequest(propertyIds);
  Predicate predicate=new PredicateBuilder().property("c1/p2").equals(1).toPredicate();
  Iterable<Resource> iterable=controller.getResources(Resource.Type.Host,request,predicate);
  int cnt=0;
  for (  Resource resource : iterable) {
    Assert.assertEquals(Resource.Type.Host,resource.getType());
    ++cnt;
  }
  Assert.assertEquals(2,cnt);
}
