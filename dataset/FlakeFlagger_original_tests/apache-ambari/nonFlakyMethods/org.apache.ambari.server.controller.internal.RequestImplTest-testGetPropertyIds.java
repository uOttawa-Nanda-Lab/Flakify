@Test public void testGetPropertyIds(){
  Request request=PropertyHelper.getReadRequest(propertyIds);
  Assert.assertEquals(propertyIds,request.getPropertyIds());
}
