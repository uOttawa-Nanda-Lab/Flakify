@Test public void testSetInvalidLength() throws Exception {
  CharArrayBuffer buffer=new CharArrayBuffer(4);
  try {
    buffer.setLength(-2);
    Assert.fail("IndexOutOfBoundsException should have been thrown");
  }
 catch (  IndexOutOfBoundsException ex) {
  }
  try {
    buffer.setLength(200);
    Assert.fail("IndexOutOfBoundsException should have been thrown");
  }
 catch (  IndexOutOfBoundsException ex) {
  }
}
