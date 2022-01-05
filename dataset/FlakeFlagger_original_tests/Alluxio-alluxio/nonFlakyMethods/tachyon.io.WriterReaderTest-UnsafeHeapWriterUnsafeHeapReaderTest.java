@Test public void UnsafeHeapWriterUnsafeHeapReaderTest() throws IOException {
  ByteBuffer buf=ByteBuffer.allocate(mDataLength);
  for (  ByteOrder order : mOrders) {
    if (order != ByteOrder.nativeOrder()) {
      continue;
    }
    buf.clear();
    buf.order(order);
    ByteBufferWriter writer=new UnsafeHeapByteBufferWriter(buf);
    generateByteBuffer(writer,order);
    ByteBufferReader reader=new UnsafeHeapByteBufferReader(writer.getByteBuffer());
    byteBufferReaderMatcher(reader,order);
  }
}
