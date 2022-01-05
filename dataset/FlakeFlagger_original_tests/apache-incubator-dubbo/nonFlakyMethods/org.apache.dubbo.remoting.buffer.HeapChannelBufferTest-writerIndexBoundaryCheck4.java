@Test public void writerIndexBoundaryCheck4(){
  buffer.writerIndex(0);
  buffer.readerIndex(0);
  buffer.writerIndex(CAPACITY);
}
