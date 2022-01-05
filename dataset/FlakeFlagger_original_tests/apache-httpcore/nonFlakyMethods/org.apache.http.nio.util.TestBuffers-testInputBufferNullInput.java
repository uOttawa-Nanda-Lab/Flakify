@Test public void testInputBufferNullInput() throws IOException {
  SimpleInputBuffer buffer=new SimpleInputBuffer(4,new DirectByteBufferAllocator());
  Assert.assertEquals(0,buffer.read(null));
  Assert.assertEquals(0,buffer.read(null,0,0));
}
