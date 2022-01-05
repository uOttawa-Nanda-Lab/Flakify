@Test public void testConstructorInvalidInput(){
  try {
    new BasicRequestLine(null,"/stuff",HttpVersion.HTTP_1_1);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException e) {
  }
  try {
    new BasicRequestLine("GET",null,HttpVersion.HTTP_1_1);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException e) {
  }
  try {
    new BasicRequestLine("GET","/stuff",(HttpVersion)null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException e) {
  }
}
