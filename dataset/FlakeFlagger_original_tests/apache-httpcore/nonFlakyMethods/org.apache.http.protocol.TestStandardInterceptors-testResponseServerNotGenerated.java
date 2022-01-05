@Test public void testResponseServerNotGenerated() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  response.getParams().setParameter(CoreProtocolPNames.ORIGIN_SERVER,"some server");
  response.addHeader(new BasicHeader(HTTP.SERVER_HEADER,"whatever"));
  ResponseServer interceptor=new ResponseServer();
  interceptor.process(response,context);
  Header h1=response.getFirstHeader(HTTP.SERVER_HEADER);
  Assert.assertNotNull(h1);
  Assert.assertEquals("whatever",h1.getValue());
}
