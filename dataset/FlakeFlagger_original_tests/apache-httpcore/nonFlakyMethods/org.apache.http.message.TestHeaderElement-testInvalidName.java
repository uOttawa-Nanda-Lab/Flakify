@Test public void testInvalidName(){
  try {
    new BasicHeaderElement(null,null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
