@Test public void UnsafeDirectWriterUnsafeDirectReaderTest() throws IOException {
  ByteBuffer buf=ByteBuffer.allocateDirect(mDataLength);
  for (  ByteOrder order : mOrders) {
    if (order != ByteOrder.nativeOrder()) {
      continue;
    }
    buf.clear();
    buf.order(order);
    ByteBufferWriter writer=new UnsafeDirectByteBufferWriter(buf);
    generateByteBuffer(writer,order);
    ByteBufferReader reader=new UnsafeDirectByteBufferReader(writer.getByteBuffer());
    byteBufferReaderMatcher(reader,order);
  }
}
