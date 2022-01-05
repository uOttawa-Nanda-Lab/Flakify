@Test public void testSimpleNot(){
  String p1=PropertyHelper.getPropertyId("cat1","prop1");
  Resource resource=new ResourceImpl(Resource.Type.Cluster);
  resource.setProperty(p1,"foo");
  PredicateBuilder pb=new PredicateBuilder();
  Predicate predicate1=pb.not().property(p1).equals("foo").toPredicate();
  Assert.assertFalse(predicate1.evaluate(resource));
  PredicateBuilder pb2=new PredicateBuilder();
  Predicate predicate2=pb2.not().property(p1).equals("bar").toPredicate();
  Assert.assertTrue(predicate2.evaluate(resource));
}
