@Test public void testGetProperties(){
  String propertyId1=PropertyHelper.getPropertyId("category1","property1");
  String propertyId2=PropertyHelper.getPropertyId("category1","property2");
  String propertyId3=PropertyHelper.getPropertyId("category1","property3");
  EqualsPredicate predicate1=new EqualsPredicate<String>(propertyId1,"v1");
  EqualsPredicate predicate2=new EqualsPredicate<String>(propertyId2,"v2");
  EqualsPredicate predicate3=new EqualsPredicate<String>(propertyId3,"v3");
  OrPredicate orPredicate=new OrPredicate(predicate1,predicate2,predicate3);
  Set<String> ids=orPredicate.getPropertyIds();
  Assert.assertEquals(3,ids.size());
  Assert.assertTrue(ids.contains(propertyId1));
  Assert.assertTrue(ids.contains(propertyId2));
  Assert.assertTrue(ids.contains(propertyId3));
}
