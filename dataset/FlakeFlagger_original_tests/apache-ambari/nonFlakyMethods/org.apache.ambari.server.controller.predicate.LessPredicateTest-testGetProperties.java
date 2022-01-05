@Test public void testGetProperties(){
  String propertyId=PropertyHelper.getPropertyId("category1","foo");
  LessPredicate predicate=new LessPredicate<Integer>(propertyId,1);
  Set<String> ids=predicate.getPropertyIds();
  Assert.assertEquals(1,ids.size());
  Assert.assertTrue(ids.contains(propertyId));
}
