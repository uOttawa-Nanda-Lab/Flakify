@Test public void testApply(){
  Resource resource=new ResourceImpl(Resource.Type.HostComponent);
  String propertyId=PropertyHelper.getPropertyId("category1","foo");
  Predicate predicate=new LessEqualsPredicate<Integer>(propertyId,10);
  resource.setProperty(propertyId,1);
  Assert.assertTrue(predicate.evaluate(resource));
  resource.setProperty(propertyId,100);
  Assert.assertFalse(predicate.evaluate(resource));
  resource.setProperty(propertyId,10);
  Assert.assertTrue(predicate.evaluate(resource));
}
