@Test public void javaWriterJavaReaderTest() throws IOException {
  ByteBuffer buf=ByteBuffer.allocate(mDataLength);
  for (  ByteOrder order : mOrders) {
    buf.clear();
    buf.order(order);
    ByteBufferWriter writer=new JavaByteBufferWriter(buf);
    generateByteBuffer(writer,order);
    ByteBufferReader reader=new JavaByteBufferReader(writer.getByteBuffer());
    byteBufferReaderMatcher(reader,order);
  }
  buf=ByteBuffer.allocateDirect(mDataLength);
  for (  ByteOrder order : mOrders) {
    buf.clear();
    buf.order(order);
    ByteBufferWriter writer=new JavaByteBufferWriter(buf);
    generateByteBuffer(writer,order);
    ByteBufferReader reader=new JavaByteBufferReader(writer.getByteBuffer());
    byteBufferReaderMatcher(reader,order);
  }
}
