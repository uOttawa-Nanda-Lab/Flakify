@Test public void testSequentialCopiedBufferTransfer1(){
  buffer.writerIndex(0);
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    byte[] value=new byte[BLOCK_SIZE];
    random.nextBytes(value);
    assertEquals(0,buffer.readerIndex());
    assertEquals(i,buffer.writerIndex());
    buffer.writeBytes(value);
  }
  random.setSeed(seed);
  byte[] expectedValue=new byte[BLOCK_SIZE];
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(expectedValue);
    assertEquals(i,buffer.readerIndex());
    assertEquals(CAPACITY,buffer.writerIndex());
    ChannelBuffer actualValue=buffer.readBytes(BLOCK_SIZE);
    assertEquals(wrappedBuffer(expectedValue),actualValue);
    actualValue.setByte(0,(byte)(actualValue.getByte(0) + 1));
    assertFalse(buffer.getByte(i) == actualValue.getByte(0));
  }
}
