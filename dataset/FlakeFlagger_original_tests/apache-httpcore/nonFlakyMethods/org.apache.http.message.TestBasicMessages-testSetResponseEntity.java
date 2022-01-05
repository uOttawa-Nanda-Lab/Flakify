@Test public void testSetResponseEntity(){
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  Assert.assertNull(response.getEntity());
  HttpEntity entity=new BasicHttpEntity();
  response.setEntity(entity);
  Assert.assertTrue(entity == response.getEntity());
}
