@Test public void testResponseConnControlHostInvalidInput() throws Exception {
  ResponseConnControl interceptor=new ResponseConnControl();
  try {
    interceptor.process(null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
    interceptor.process(response,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
