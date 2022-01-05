@Test public void testResponseContentEntityChunkedHTTP10() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_0,HttpStatus.SC_OK,"OK");
  BasicHttpEntity entity=new BasicHttpEntity();
  entity.setChunked(true);
  response.setEntity(entity);
  ResponseContent interceptor=new ResponseContent();
  interceptor.process(response,context);
  Header h1=response.getFirstHeader(HTTP.TRANSFER_ENCODING);
  Assert.assertNull(h1);
  Header h2=response.getFirstHeader(HTTP.CONTENT_LEN);
  Assert.assertNull(h2);
}
