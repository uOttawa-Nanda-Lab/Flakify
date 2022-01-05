@Test public void testSetRequestEntity(){
  BasicHttpEntityEnclosingRequest request=new BasicHttpEntityEnclosingRequest("GET","/");
  Assert.assertNull(request.getEntity());
  HttpEntity entity=new BasicHttpEntity();
  request.setEntity(entity);
  Assert.assertTrue(entity == request.getEntity());
}
