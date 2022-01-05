@Test public void testRandomHeapBufferTransfer1(){
  byte[] valueContent=new byte[BLOCK_SIZE];
  ChannelBuffer value=wrappedBuffer(valueContent);
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(valueContent);
    value.setIndex(0,BLOCK_SIZE);
    buffer.setBytes(i,value);
    assertEquals(BLOCK_SIZE,value.readerIndex());
    assertEquals(BLOCK_SIZE,value.writerIndex());
  }
  random.setSeed(seed);
  byte[] expectedValueContent=new byte[BLOCK_SIZE];
  ChannelBuffer expectedValue=wrappedBuffer(expectedValueContent);
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(expectedValueContent);
    value.clear();
    buffer.getBytes(i,value);
    assertEquals(0,value.readerIndex());
    assertEquals(BLOCK_SIZE,value.writerIndex());
    for (int j=0; j < BLOCK_SIZE; j++) {
      assertEquals(expectedValue.getByte(j),value.getByte(j));
    }
  }
}
