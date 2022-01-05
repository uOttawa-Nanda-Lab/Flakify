@Test public void UnsafeHeapWriterJavaReaderTest() throws IOException {
  ByteBuffer buf=ByteBuffer.allocate(mDataLength);
  for (  ByteOrder order : mOrders) {
    if (order != ByteOrder.nativeOrder()) {
      continue;
    }
    buf.clear();
    buf.order(order);
    ByteBufferWriter writer=new UnsafeHeapByteBufferWriter(buf);
    generateByteBuffer(writer,order);
    ByteBufferReader reader=new JavaByteBufferReader(writer.getByteBuffer());
    byteBufferReaderMatcher(reader,order);
  }
}
