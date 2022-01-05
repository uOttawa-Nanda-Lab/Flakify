@Test public void testAltProperty(){
  String p1="cat1/prop1";
  String p2="cat1/prop2";
  String p3="prop3";
  Resource resource=new ResourceImpl(Resource.Type.Cluster);
  resource.setProperty(p1,"foo");
  resource.setProperty(p2,"bar");
  resource.setProperty(p3,"cat");
  PredicateBuilder pb1=new PredicateBuilder();
  Predicate predicate1=pb1.begin().property("cat1/prop1").equals("foo").and().property("cat1/prop2").equals("bar").end().or().property("prop3").equals("cat").toPredicate();
  Assert.assertTrue(predicate1.evaluate(resource));
}
