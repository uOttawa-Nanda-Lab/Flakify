@Test public void testApply(){
  Resource resource=new ResourceImpl(Resource.Type.HostComponent);
  String propertyId=PropertyHelper.getPropertyId("category1","foo");
  Predicate predicate=new EqualsPredicate<String>(propertyId,"bar");
  resource.setProperty(propertyId,"monkey");
  Assert.assertFalse(predicate.evaluate(resource));
  resource.setProperty(propertyId,"bar");
  Assert.assertTrue(predicate.evaluate(resource));
  propertyId=PropertyHelper.getPropertyId("category1","fun");
  predicate=new EqualsPredicate<String>(propertyId,"bar");
  Assert.assertFalse(predicate.evaluate(resource));
}
