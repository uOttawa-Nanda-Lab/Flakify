@Test public void testResponseServerMissing() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  ResponseServer interceptor=new ResponseServer();
  interceptor.process(response,context);
  Header h1=response.getFirstHeader(HTTP.SERVER_HEADER);
  Assert.assertNull(h1);
}
