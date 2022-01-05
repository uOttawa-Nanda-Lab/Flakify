@Test(expected=IndexOutOfBoundsException.class) public void writerIndexBoundaryCheck2(){
  try {
    buffer.writerIndex(CAPACITY);
    buffer.readerIndex(CAPACITY);
  }
 catch (  IndexOutOfBoundsException e) {
    fail();
  }
  buffer.writerIndex(buffer.capacity() + 1);
}
