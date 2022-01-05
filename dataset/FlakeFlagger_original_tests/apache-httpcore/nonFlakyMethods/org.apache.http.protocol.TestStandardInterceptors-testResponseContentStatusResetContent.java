@Test public void testResponseContentStatusResetContent() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  response.setStatusCode(HttpStatus.SC_RESET_CONTENT);
  ResponseContent interceptor=new ResponseContent();
  interceptor.process(response,context);
  Header header=response.getFirstHeader(HTTP.CONTENT_LEN);
  Assert.assertNull(header);
}
