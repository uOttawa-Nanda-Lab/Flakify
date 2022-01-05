@Test public void testSequentialByteBufferBackedHeapBufferTransfer1(){
  byte[] valueContent=new byte[BLOCK_SIZE * 2];
  ChannelBuffer value=wrappedBuffer(ByteBuffer.allocate(BLOCK_SIZE * 2));
  value.writerIndex(0);
  buffer.writerIndex(0);
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(valueContent);
    value.setBytes(0,valueContent);
    assertEquals(0,buffer.readerIndex());
    assertEquals(i,buffer.writerIndex());
    buffer.writeBytes(value,random.nextInt(BLOCK_SIZE),BLOCK_SIZE);
    assertEquals(0,value.readerIndex());
    assertEquals(0,value.writerIndex());
  }
  random.setSeed(seed);
  byte[] expectedValueContent=new byte[BLOCK_SIZE * 2];
  ChannelBuffer expectedValue=wrappedBuffer(expectedValueContent);
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(expectedValueContent);
    int valueOffset=random.nextInt(BLOCK_SIZE);
    value.setBytes(0,valueContent);
    assertEquals(i,buffer.readerIndex());
    assertEquals(CAPACITY,buffer.writerIndex());
    buffer.readBytes(value,valueOffset,BLOCK_SIZE);
    for (int j=valueOffset; j < valueOffset + BLOCK_SIZE; j++) {
      assertEquals(expectedValue.getByte(j),value.getByte(j));
    }
    assertEquals(0,value.readerIndex());
    assertEquals(0,value.writerIndex());
  }
}
