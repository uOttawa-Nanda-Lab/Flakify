@Test public void testConstructor() throws Exception {
  new BasicHttpRequest("GET","/stuff");
  new BasicHttpRequest("GET","/stuff",HttpVersion.HTTP_1_1);
  try {
    new BasicHttpRequest(null,"/stuff");
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new BasicHttpRequest("GET",null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
