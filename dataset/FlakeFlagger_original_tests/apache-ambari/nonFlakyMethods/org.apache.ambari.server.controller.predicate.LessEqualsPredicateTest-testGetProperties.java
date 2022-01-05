@Test public void testGetProperties(){
  String propertyId=PropertyHelper.getPropertyId("category1","foo");
  LessEqualsPredicate predicate=new LessEqualsPredicate<Integer>(propertyId,10);
  Set<String> ids=predicate.getPropertyIds();
  Assert.assertEquals(1,ids.size());
  Assert.assertTrue(ids.contains(propertyId));
}
