@Test public void testResponseContentEntityChunked() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  BasicHttpEntity entity=new BasicHttpEntity();
  entity.setChunked(true);
  response.setEntity(entity);
  ResponseContent interceptor=new ResponseContent();
  interceptor.process(response,context);
  Header h1=response.getFirstHeader(HTTP.TRANSFER_ENCODING);
  Assert.assertNotNull(h1);
  Assert.assertEquals(HTTP.CHUNK_CODING,h1.getValue());
  Header h2=response.getFirstHeader(HTTP.CONTENT_LEN);
  Assert.assertNull(h2);
}
