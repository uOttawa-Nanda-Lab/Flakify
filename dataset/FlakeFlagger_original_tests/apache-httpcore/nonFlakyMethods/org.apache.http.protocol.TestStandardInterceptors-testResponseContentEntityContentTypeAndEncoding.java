@Test public void testResponseContentEntityContentTypeAndEncoding() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  BasicHttpEntity entity=new BasicHttpEntity();
  entity.setContentEncoding("whatever");
  entity.setContentType("whatever");
  response.setEntity(entity);
  ResponseContent interceptor=new ResponseContent();
  interceptor.process(response,context);
  Header h1=response.getFirstHeader(HTTP.CONTENT_TYPE);
  Assert.assertNotNull(h1);
  Assert.assertEquals("whatever",h1.getValue());
  Header h2=response.getFirstHeader(HTTP.CONTENT_ENCODING);
  Assert.assertNotNull(h2);
  Assert.assertEquals("whatever",h2.getValue());
}
