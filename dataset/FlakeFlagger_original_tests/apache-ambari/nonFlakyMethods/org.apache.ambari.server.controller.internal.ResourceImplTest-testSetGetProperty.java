@Test public void testSetGetProperty(){
  Resource resource=new ResourceImpl(Resource.Type.Cluster);
  String propertyId=PropertyHelper.getPropertyId("c1","p1");
  resource.setProperty(propertyId,"foo");
  Assert.assertEquals("foo",resource.getPropertyValue(propertyId));
  resource.setProperty(propertyId,1);
  Assert.assertEquals(1,resource.getPropertyValue(propertyId));
  resource.setProperty(propertyId,(float)1.99);
  Assert.assertEquals((float)1.99,resource.getPropertyValue(propertyId));
  resource.setProperty(propertyId,1.99);
  Assert.assertEquals(1.99,resource.getPropertyValue(propertyId));
  resource.setProperty(propertyId,65L);
  Assert.assertEquals(65L,resource.getPropertyValue(propertyId));
}
