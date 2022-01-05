@Test public void testSequentialByteBufferBackedHeapBufferTransfer2(){
  byte[] valueContent=new byte[BLOCK_SIZE * 2];
  ChannelBuffer value=wrappedBuffer(ByteBuffer.allocate(BLOCK_SIZE * 2));
  value.writerIndex(0);
  buffer.writerIndex(0);
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(valueContent);
    value.setBytes(0,valueContent);
    assertEquals(0,buffer.readerIndex());
    assertEquals(i,buffer.writerIndex());
    int readerIndex=random.nextInt(BLOCK_SIZE);
    value.readerIndex(0);
    value.writerIndex(readerIndex + BLOCK_SIZE);
    value.readerIndex(readerIndex);
    buffer.writeBytes(value);
    assertEquals(readerIndex + BLOCK_SIZE,value.writerIndex());
    assertEquals(value.writerIndex(),value.readerIndex());
  }
  random.setSeed(seed);
  byte[] expectedValueContent=new byte[BLOCK_SIZE * 2];
  ChannelBuffer expectedValue=wrappedBuffer(expectedValueContent);
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(expectedValueContent);
    value.setBytes(0,valueContent);
    int valueOffset=random.nextInt(BLOCK_SIZE);
    assertEquals(i,buffer.readerIndex());
    assertEquals(CAPACITY,buffer.writerIndex());
    value.readerIndex(valueOffset);
    value.writerIndex(valueOffset);
    buffer.readBytes(value,BLOCK_SIZE);
    for (int j=valueOffset; j < valueOffset + BLOCK_SIZE; j++) {
      assertEquals(expectedValue.getByte(j),value.getByte(j));
    }
    assertEquals(valueOffset,value.readerIndex());
    assertEquals(valueOffset + BLOCK_SIZE,value.writerIndex());
  }
}
