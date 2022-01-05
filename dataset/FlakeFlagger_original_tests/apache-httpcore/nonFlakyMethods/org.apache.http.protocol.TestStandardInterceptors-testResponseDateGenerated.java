@Test public void testResponseDateGenerated() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  ResponseDate interceptor=new ResponseDate();
  interceptor.process(response,context);
  Header h1=response.getFirstHeader(HTTP.DATE_HEADER);
  Assert.assertNotNull(h1);
  interceptor.process(response,context);
  Header h2=response.getFirstHeader(HTTP.DATE_HEADER);
  Assert.assertNotNull(h2);
}
