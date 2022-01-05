@Test public void testInvalidName(){
  try {
    new BufferedHeader(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
