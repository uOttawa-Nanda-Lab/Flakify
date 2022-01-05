@Test public void testStreamTransfer2() throws Exception {
  byte[] expected=new byte[buffer.capacity()];
  random.nextBytes(expected);
  buffer.clear();
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    ByteArrayInputStream in=new ByteArrayInputStream(expected,i,BLOCK_SIZE);
    assertEquals(i,buffer.writerIndex());
    buffer.writeBytes(in,BLOCK_SIZE);
    assertEquals(i + BLOCK_SIZE,buffer.writerIndex());
  }
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    assertEquals(i,buffer.readerIndex());
    buffer.readBytes(out,BLOCK_SIZE);
    assertEquals(i + BLOCK_SIZE,buffer.readerIndex());
  }
  assertTrue(Arrays.equals(expected,out.toByteArray()));
}
