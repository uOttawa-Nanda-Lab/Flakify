@Test public void readerIndexBoundaryCheck4(){
  buffer.writerIndex(0);
  buffer.readerIndex(0);
  buffer.writerIndex(buffer.capacity());
  buffer.readerIndex(buffer.capacity());
}
