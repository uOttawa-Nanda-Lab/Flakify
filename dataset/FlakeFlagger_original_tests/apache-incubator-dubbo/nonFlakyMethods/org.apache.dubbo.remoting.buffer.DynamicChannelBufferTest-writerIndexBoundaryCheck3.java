@Test(expected=IndexOutOfBoundsException.class) public void writerIndexBoundaryCheck3(){
  try {
    buffer.writerIndex(CAPACITY);
    buffer.readerIndex(CAPACITY / 2);
  }
 catch (  IndexOutOfBoundsException e) {
    fail();
  }
  buffer.writerIndex(CAPACITY / 4);
}
