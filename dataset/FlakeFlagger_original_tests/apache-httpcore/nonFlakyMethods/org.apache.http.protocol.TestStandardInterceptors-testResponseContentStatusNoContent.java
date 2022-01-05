@Test public void testResponseContentStatusNoContent() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  response.setStatusCode(HttpStatus.SC_NO_CONTENT);
  ResponseContent interceptor=new ResponseContent();
  interceptor.process(response,context);
  Header header=response.getFirstHeader(HTTP.CONTENT_LEN);
  Assert.assertNull(header);
}
