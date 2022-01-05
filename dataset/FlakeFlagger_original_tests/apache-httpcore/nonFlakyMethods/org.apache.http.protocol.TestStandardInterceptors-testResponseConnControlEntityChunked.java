@Test public void testResponseConnControlEntityChunked() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  BasicHttpEntity entity=new BasicHttpEntity();
  entity.setChunked(true);
  response.setEntity(entity);
  ResponseConnControl interceptor=new ResponseConnControl();
  interceptor.process(response,context);
  Header header=response.getFirstHeader(HTTP.CONN_DIRECTIVE);
  Assert.assertNull(header);
}
