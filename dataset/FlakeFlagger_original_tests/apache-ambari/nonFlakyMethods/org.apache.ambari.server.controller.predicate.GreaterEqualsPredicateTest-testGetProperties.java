@Test public void testGetProperties(){
  String propertyId=PropertyHelper.getPropertyId("category1","foo");
  GreaterEqualsPredicate predicate=new GreaterEqualsPredicate<Integer>(propertyId,10);
  Set<String> ids=predicate.getPropertyIds();
  Assert.assertEquals(1,ids.size());
  Assert.assertTrue(ids.contains(propertyId));
}
