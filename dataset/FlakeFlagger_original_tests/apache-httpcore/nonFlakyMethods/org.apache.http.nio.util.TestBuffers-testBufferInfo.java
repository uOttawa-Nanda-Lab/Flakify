@Test public void testBufferInfo() throws Exception {
  SimpleOutputBuffer buffer=new SimpleOutputBuffer(8,new DirectByteBufferAllocator());
  BufferInfo bufferinfo=buffer;
  Assert.assertEquals(0,bufferinfo.length());
  Assert.assertEquals(8,bufferinfo.available());
  buffer.write(new byte[]{'1','2','3','4'});
  Assert.assertEquals(4,bufferinfo.length());
  Assert.assertEquals(4,bufferinfo.available());
  buffer.write(new byte[]{'1','2','3','4','5','6','7','8'});
  Assert.assertEquals(12,bufferinfo.length());
  Assert.assertEquals(0,bufferinfo.available());
}
