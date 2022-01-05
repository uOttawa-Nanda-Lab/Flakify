@Test public void testResponseConnControlNoEntity() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  ResponseConnControl interceptor=new ResponseConnControl();
  interceptor.process(response,context);
  Header header=response.getFirstHeader(HTTP.CONN_DIRECTIVE);
  Assert.assertNull(header);
}
