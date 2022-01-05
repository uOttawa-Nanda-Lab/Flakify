@Test public void testOutputBufferNullInput() throws IOException {
  SimpleOutputBuffer buffer=new SimpleOutputBuffer(4,new DirectByteBufferAllocator());
  buffer.write(null);
  buffer.write(null,0,10);
  Assert.assertFalse(buffer.hasData());
}
