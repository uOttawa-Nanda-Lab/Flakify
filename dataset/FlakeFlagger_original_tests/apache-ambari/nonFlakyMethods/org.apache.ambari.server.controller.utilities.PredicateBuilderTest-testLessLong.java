@Test public void testLessLong(){
  String p1=PropertyHelper.getPropertyId("cat1","prop1");
  Resource resource=new ResourceImpl(Resource.Type.Cluster);
  resource.setProperty(p1,2L);
  PredicateBuilder pb=new PredicateBuilder();
  Predicate predicate1=pb.property(p1).lessThan(99L).toPredicate();
  Assert.assertTrue(predicate1.evaluate(resource));
  PredicateBuilder pb2=new PredicateBuilder();
  Predicate predicate2=pb2.property(p1).lessThan(1L).toPredicate();
  Assert.assertFalse(predicate2.evaluate(resource));
}
