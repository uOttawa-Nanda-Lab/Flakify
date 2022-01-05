@Test public void testLongAnd(){
  String p1=PropertyHelper.getPropertyId("cat1","prop1");
  String p2=PropertyHelper.getPropertyId("cat1","prop2");
  String p3=PropertyHelper.getPropertyId("cat1","prop3");
  String p4=PropertyHelper.getPropertyId("cat1","prop4");
  Resource resource=new ResourceImpl(Resource.Type.Cluster);
  resource.setProperty(p1,"foo");
  resource.setProperty(p2,"bar");
  resource.setProperty(p3,"cat");
  resource.setProperty(p4,"dog");
  PredicateBuilder pb=new PredicateBuilder();
  Predicate predicate1=pb.property(p1).equals("foo").and().property(p2).equals("bar").and().property(p3).equals("cat").and().property(p4).equals("dog").toPredicate();
  Assert.assertTrue(predicate1.evaluate(resource));
  PredicateBuilder pb2=new PredicateBuilder();
  Predicate predicate2=pb2.property(p1).equals("foo").and().property(p2).equals("bar").and().property(p3).equals("cat").and().property(p4).equals("dot").toPredicate();
  Assert.assertFalse(predicate2.evaluate(resource));
}
