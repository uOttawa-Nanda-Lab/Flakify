@Test public void testApply(){
  Resource resource=new ResourceImpl(Resource.Type.HostComponent);
  String propertyId1=PropertyHelper.getPropertyId("category1","property1");
  String propertyId2=PropertyHelper.getPropertyId("category1","property2");
  String propertyId3=PropertyHelper.getPropertyId("category1","property3");
  EqualsPredicate predicate1=new EqualsPredicate<String>(propertyId1,"v1");
  EqualsPredicate predicate2=new EqualsPredicate<String>(propertyId2,"v2");
  EqualsPredicate predicate3=new EqualsPredicate<String>(propertyId3,"v3");
  OrPredicate orPredicate=new OrPredicate(predicate1,predicate2,predicate3);
  resource.setProperty(propertyId1,"big");
  resource.setProperty(propertyId2,"monkey");
  resource.setProperty(propertyId3,"runner");
  Assert.assertFalse(orPredicate.evaluate(resource));
  resource.setProperty(propertyId2,"v2");
  Assert.assertTrue(orPredicate.evaluate(resource));
}
