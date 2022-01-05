@Test public void testApply(){
  Resource resource=new ResourceImpl(Resource.Type.HostComponent);
  String propertyId=PropertyHelper.getPropertyId("category1","foo");
  EqualsPredicate predicate=new EqualsPredicate<String>(propertyId,"bar");
  NotPredicate notPredicate=new NotPredicate(predicate);
  resource.setProperty(propertyId,"monkey");
  Assert.assertTrue(notPredicate.evaluate(resource));
  resource.setProperty(propertyId,"bar");
  Assert.assertFalse(notPredicate.evaluate(resource));
}
