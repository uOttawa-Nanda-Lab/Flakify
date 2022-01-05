@Test public void testHttpVersionInvalidConstructorInput() throws Exception {
  try {
    new HttpVersion(-1,-1);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException e) {
  }
  try {
    new HttpVersion(0,-1);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException e) {
  }
}
