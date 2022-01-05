@Test public void testStreamTransfer1() throws Exception {
  byte[] expected=new byte[buffer.capacity()];
  random.nextBytes(expected);
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    ByteArrayInputStream in=new ByteArrayInputStream(expected,i,BLOCK_SIZE);
    assertEquals(BLOCK_SIZE,buffer.setBytes(i,in,BLOCK_SIZE));
    assertEquals(-1,buffer.setBytes(i,in,0));
  }
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  for (int i=0; i < buffer.capacity() - BLOCK_SIZE + 1; i+=BLOCK_SIZE) {
    buffer.getBytes(i,out,BLOCK_SIZE);
  }
  assertTrue(Arrays.equals(expected,out.toByteArray()));
}
