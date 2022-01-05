@Test public void testAndOr(){
  String p1=PropertyHelper.getPropertyId("cat1","prop1");
  String p2=PropertyHelper.getPropertyId("cat1","prop2");
  String p3=PropertyHelper.getPropertyId("cat1","prop3");
  Resource resource=new ResourceImpl(Resource.Type.Cluster);
  resource.setProperty(p1,"foo");
  resource.setProperty(p2,"bar");
  resource.setProperty(p3,"cat");
  PredicateBuilder pb1=new PredicateBuilder();
  Predicate predicate1=pb1.property(p1).equals("foo").and().property(p2).equals("bar").or().property(p3).equals("cat").toPredicate();
  Assert.assertTrue(predicate1.evaluate(resource));
  PredicateBuilder pb2=new PredicateBuilder();
  Predicate predicate2=pb2.property(p1).equals("foo").and().property(p2).equals("car").or().property(p3).equals("cat").toPredicate();
  Assert.assertTrue(predicate2.evaluate(resource));
  PredicateBuilder pb3=new PredicateBuilder();
  Predicate predicate3=pb3.property(p1).equals("foo").and().property(p2).equals("bar").or().property(p3).equals("can").toPredicate();
  Assert.assertTrue(predicate3.evaluate(resource));
  PredicateBuilder pb4=new PredicateBuilder();
  Predicate predicate4=pb4.property(p1).equals("foo").and().property(p2).equals("bat").or().property(p3).equals("can").toPredicate();
  Assert.assertFalse(predicate4.evaluate(resource));
}
