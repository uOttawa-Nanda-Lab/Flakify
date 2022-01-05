@Test(expected=IndexOutOfBoundsException.class) public void readerIndexBoundaryCheck3(){
  try {
    buffer.writerIndex(CAPACITY / 2);
  }
 catch (  IndexOutOfBoundsException e) {
    fail();
  }
  buffer.readerIndex(CAPACITY * 3 / 2);
}
