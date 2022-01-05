@Test public void testRandomHeapBufferTransfer2(){
  byte[] valueContent=new byte[BLOCK_SIZE * 2];
  ChannelBuffer value=wrappedBuffer(valueContent);
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(valueContent);
    buffer.setBytes(i,value,random.nextInt(BLOCK_SIZE),BLOCK_SIZE);
  }
  random.setSeed(seed);
  byte[] expectedValueContent=new byte[BLOCK_SIZE * 2];
  ChannelBuffer expectedValue=wrappedBuffer(expectedValueContent);
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(expectedValueContent);
    int valueOffset=random.nextInt(BLOCK_SIZE);
    buffer.getBytes(i,value,valueOffset,BLOCK_SIZE);
    for (int j=valueOffset; j < valueOffset + BLOCK_SIZE; j++) {
      assertEquals(expectedValue.getByte(j),value.getByte(j));
    }
  }
}
