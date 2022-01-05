@Test public void testSimple(){
  String p1=PropertyHelper.getPropertyId("cat1","prop1");
  Resource resource=new ResourceImpl(Resource.Type.Cluster);
  resource.setProperty(p1,"foo");
  PredicateBuilder pb=new PredicateBuilder();
  Predicate predicate1=pb.property(p1).equals("foo").toPredicate();
  Assert.assertTrue(predicate1.evaluate(resource));
  PredicateBuilder pb2=new PredicateBuilder();
  Predicate predicate2=pb2.property(p1).equals("bar").toPredicate();
  Assert.assertFalse(predicate2.evaluate(resource));
}
