@Test public void testRandomDirectBufferTransfer(){
  byte[] tmp=new byte[BLOCK_SIZE * 2];
  ChannelBuffer value=directBuffer(BLOCK_SIZE * 2);
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(tmp);
    value.setBytes(0,tmp,0,value.capacity());
    buffer.setBytes(i,value,random.nextInt(BLOCK_SIZE),BLOCK_SIZE);
  }
  random.setSeed(seed);
  ChannelBuffer expectedValue=directBuffer(BLOCK_SIZE * 2);
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(tmp);
    expectedValue.setBytes(0,tmp,0,expectedValue.capacity());
    int valueOffset=random.nextInt(BLOCK_SIZE);
    buffer.getBytes(i,value,valueOffset,BLOCK_SIZE);
    for (int j=valueOffset; j < valueOffset + BLOCK_SIZE; j++) {
      assertEquals(expectedValue.getByte(j),value.getByte(j));
    }
  }
}
