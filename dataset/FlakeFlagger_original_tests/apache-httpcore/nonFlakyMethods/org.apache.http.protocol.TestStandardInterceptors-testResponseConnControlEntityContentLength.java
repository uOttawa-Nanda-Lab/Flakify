@Test public void testResponseConnControlEntityContentLength() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  StringEntity entity=new StringEntity("whatever");
  response.setEntity(entity);
  ResponseConnControl interceptor=new ResponseConnControl();
  interceptor.process(response,context);
  Header header=response.getFirstHeader(HTTP.CONN_DIRECTIVE);
  Assert.assertNull(header);
}
