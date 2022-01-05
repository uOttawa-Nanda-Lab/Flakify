@Test public void testRequestConnControlCustom() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  BasicHttpRequest request=new BasicHttpRequest("GET","/");
  Header myheader=new BasicHeader(HTTP.CONN_DIRECTIVE,HTTP.CONN_CLOSE);
  request.addHeader(myheader);
  RequestConnControl interceptor=new RequestConnControl();
  interceptor.process(request,context);
  Header header=request.getFirstHeader(HTTP.CONN_DIRECTIVE);
  Assert.assertNotNull(header);
  Assert.assertEquals(HTTP.CONN_CLOSE,header.getValue());
  Assert.assertTrue(header == myheader);
}
