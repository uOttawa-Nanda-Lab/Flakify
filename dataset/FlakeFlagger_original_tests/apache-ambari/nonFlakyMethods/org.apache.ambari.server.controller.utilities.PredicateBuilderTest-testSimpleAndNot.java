@Test public void testSimpleAndNot(){
  String p1=PropertyHelper.getPropertyId("cat1","prop1");
  String p2=PropertyHelper.getPropertyId("cat1","prop2");
  Resource resource=new ResourceImpl(Resource.Type.Cluster);
  resource.setProperty(p1,"foo");
  resource.setProperty(p2,"bar");
  PredicateBuilder pb=new PredicateBuilder();
  Predicate predicate1=pb.property(p1).equals("foo").and().not().property(p2).equals("bar").toPredicate();
  Assert.assertFalse(predicate1.evaluate(resource));
  PredicateBuilder pb2=new PredicateBuilder();
  Predicate predicate2=pb2.property(p1).equals("foo").and().not().property(p2).equals("car").toPredicate();
  Assert.assertTrue(predicate2.evaluate(resource));
}
