@Test public void testResponseServerGenerated() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  response.getParams().setParameter(CoreProtocolPNames.ORIGIN_SERVER,"some server");
  ResponseServer interceptor=new ResponseServer();
  interceptor.process(response,context);
  Header h1=response.getFirstHeader(HTTP.SERVER_HEADER);
  Assert.assertNotNull(h1);
  Assert.assertEquals("some server",h1.getValue());
}
