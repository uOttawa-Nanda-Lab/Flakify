@Test public void testSequentialByteArrayTransfer1(){
  byte[] value=new byte[BLOCK_SIZE];
  buffer.writerIndex(0);
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
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
    buffer.readBytes(value);
    for (int j=0; j < BLOCK_SIZE; j++) {
      assertEquals(expectedValue[j],value[j]);
    }
  }
}
