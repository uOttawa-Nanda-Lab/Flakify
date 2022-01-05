@Test public void testResponseContentEntityNoContentTypeAndEncoding() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  BasicHttpEntity entity=new BasicHttpEntity();
  response.setEntity(entity);
  ResponseContent interceptor=new ResponseContent();
  interceptor.process(response,context);
  Header h1=response.getFirstHeader(HTTP.CONTENT_TYPE);
  Assert.assertNull(h1);
  Header h2=response.getFirstHeader(HTTP.CONTENT_ENCODING);
  Assert.assertNull(h2);
}
