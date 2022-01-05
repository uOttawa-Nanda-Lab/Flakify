@Test public void testUnbalancedBlocks(){
  String p1=PropertyHelper.getPropertyId("cat1","prop1");
  String p2=PropertyHelper.getPropertyId("cat1","prop2");
  String p3=PropertyHelper.getPropertyId("cat1","prop3");
  String p4=PropertyHelper.getPropertyId("cat1","prop4");
  Resource resource=new ResourceImpl(Resource.Type.Cluster);
  resource.setProperty(p1,"foo");
  resource.setProperty(p2,"bar");
  resource.setProperty(p3,"cat");
  resource.setProperty(p4,"dog");
  PredicateBuilder pb1=new PredicateBuilder();
  try {
    pb1.begin().property(p1).equals("foo").and().begin().property(p2).equals("bar").or().property(p3).equals("cat").end().or().property(p4).equals("dog").toPredicate();
    Assert.fail("Expected IllegalStateException.");
  }
 catch (  IllegalStateException e) {
  }
  PredicateBuilder pb2=new PredicateBuilder();
  try {
    pb2.begin().property(p1).equals("foo").and().property(p2).equals("bar").or().property(p3).equals("cat").end().end().or().property(p4).equals("dog").toPredicate();
    Assert.fail("Expected IllegalStateException.");
  }
 catch (  IllegalStateException e) {
  }
}
