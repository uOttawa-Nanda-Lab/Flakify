@Test public void testConstructorInvalidInput(){
  try {
    new BasicStatusLine(null,HttpStatus.SC_OK,"OK");
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException e) {
  }
  try {
    new BasicStatusLine(HttpVersion.HTTP_1_1,-1,"OK");
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException e) {
  }
}
