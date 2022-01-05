@Test public void testInvalidConstructor(){
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,128,params);
  try {
    new DefaultHttpRequestParser(null,null,null,params);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new DefaultHttpRequestParser(inbuf,null,null,params);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new DefaultHttpResponseParser(null,null,null,params);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new DefaultHttpResponseParser(inbuf,null,null,params);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
