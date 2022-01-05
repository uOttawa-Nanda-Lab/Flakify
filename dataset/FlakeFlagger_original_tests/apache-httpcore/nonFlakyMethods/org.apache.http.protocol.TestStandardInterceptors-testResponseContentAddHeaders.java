@Test public void testResponseContentAddHeaders() throws Exception {
  ResponseContent interceptor=new ResponseContent(true);
  HttpContext context=new BasicHttpContext(null);
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  interceptor.process(response,context);
  Assert.assertEquals("0",response.getFirstHeader(HTTP.CONTENT_LEN).getValue());
  Assert.assertNull(response.getFirstHeader(HTTP.TRANSFER_ENCODING));
}
