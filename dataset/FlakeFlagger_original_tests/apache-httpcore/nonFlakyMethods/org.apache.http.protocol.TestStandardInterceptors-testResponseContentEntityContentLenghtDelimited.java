@Test public void testResponseContentEntityContentLenghtDelimited() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  BasicHttpEntity entity=new BasicHttpEntity();
  entity.setContentLength(10);
  response.setEntity(entity);
  ResponseContent interceptor=new ResponseContent();
  interceptor.process(response,context);
  Header h1=response.getFirstHeader(HTTP.CONTENT_LEN);
  Assert.assertNotNull(h1);
  Assert.assertEquals("10",h1.getValue());
  Header h2=response.getFirstHeader(HTTP.TRANSFER_ENCODING);
  Assert.assertNull(h2);
}
