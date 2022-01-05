@Test public void testInvalidInput(){
  HttpMessage message=new TestHttpMessage();
  try {
    message.setParams(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    message.addHeader(null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    message.setHeader(null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
