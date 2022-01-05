@Test public void testByteArrayTransfer(){
  byte[] value=new byte[BLOCK_SIZE * 2];
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(value);
    buffer.setBytes(i,value,random.nextInt(BLOCK_SIZE),BLOCK_SIZE);
  }
  random.setSeed(seed);
  byte[] expectedValue=new byte[BLOCK_SIZE * 2];
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    random.nextBytes(expectedValue);
    int valueOffset=random.nextInt(BLOCK_SIZE);
    buffer.getBytes(i,value,valueOffset,BLOCK_SIZE);
    for (int j=valueOffset; j < valueOffset + BLOCK_SIZE; j++) {
      assertEquals(expectedValue[j],value[j]);
    }
  }
}
