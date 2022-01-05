@Test public void testDone(){
  String p1=PropertyHelper.getPropertyId("cat1","prop1");
  Resource resource=new ResourceImpl(Resource.Type.Cluster);
  resource.setProperty(p1,"foo");
  PredicateBuilder pb=new PredicateBuilder();
  Predicate predicate=pb.property(p1).equals("foo").toPredicate();
  try {
    pb.property(p1).equals("foo").toPredicate();
    Assert.fail("Expected IllegalStateException.");
  }
 catch (  IllegalStateException e) {
  }
  Assert.assertSame(predicate,pb.toPredicate());
}
