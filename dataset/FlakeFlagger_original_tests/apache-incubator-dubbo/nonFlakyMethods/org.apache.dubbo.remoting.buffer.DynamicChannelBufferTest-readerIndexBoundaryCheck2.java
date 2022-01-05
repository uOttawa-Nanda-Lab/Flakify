@Test(expected=IndexOutOfBoundsException.class) public void readerIndexBoundaryCheck2(){
  try {
    buffer.writerIndex(buffer.capacity());
  }
 catch (  IndexOutOfBoundsException e) {
    fail();
  }
  buffer.readerIndex(buffer.capacity() + 1);
}
