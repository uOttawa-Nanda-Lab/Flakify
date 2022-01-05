@Test public void testInvalidHEArguments() throws Exception {
  try {
    BasicHeaderValueFormatter.formatHeaderElement((HeaderElement)null,false,BasicHeaderValueFormatter.DEFAULT);
    Assert.fail("IllegalArgumentException should habe been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    BasicHeaderValueFormatter.formatElements((HeaderElement[])null,false,BasicHeaderValueFormatter.DEFAULT);
    Assert.fail("IllegalArgumentException should habe been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
