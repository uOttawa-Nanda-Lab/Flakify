@Test public void testGetProperties(){
  String propertyId=PropertyHelper.getPropertyId("category1","foo");
  EqualsPredicate predicate=new EqualsPredicate<String>(propertyId,"bar");
  Set<String> ids=predicate.getPropertyIds();
  Assert.assertEquals(1,ids.size());
  Assert.assertTrue(ids.contains(propertyId));
}
