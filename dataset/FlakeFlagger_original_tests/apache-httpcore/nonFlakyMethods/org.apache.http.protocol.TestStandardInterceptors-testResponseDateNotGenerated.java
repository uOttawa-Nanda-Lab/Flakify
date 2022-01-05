@Test public void testResponseDateNotGenerated() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  response.setStatusCode(199);
  ResponseDate interceptor=new ResponseDate();
  interceptor.process(response,context);
  Header h1=response.getFirstHeader(HTTP.DATE_HEADER);
  Assert.assertNull(h1);
}
