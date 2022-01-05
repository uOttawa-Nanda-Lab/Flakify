@Test(expected=IndexOutOfBoundsException.class) public void readerIndexBoundaryCheck1(){
  try {
    buffer.writerIndex(0);
  }
 catch (  IndexOutOfBoundsException e) {
    fail();
  }
  buffer.readerIndex(-1);
}
