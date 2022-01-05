@Test public void getByteOfEmptyBuffer() throws Exception {
  OkBuffer buffer=new OkBuffer();
  try {
    buffer.getByte(0);
    fail();
  }
 catch (  IndexOutOfBoundsException expected) {
  }
}
