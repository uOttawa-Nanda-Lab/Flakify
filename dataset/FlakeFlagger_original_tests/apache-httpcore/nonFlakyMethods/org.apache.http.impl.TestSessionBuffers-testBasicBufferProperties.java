@Test public void testBasicBufferProperties() throws Exception {
  SessionInputBufferMock inbuffer=new SessionInputBufferMock(new byte[]{1,2,3});
  Assert.assertEquals(SessionInputBufferMock.BUFFER_SIZE,inbuffer.capacity());
  Assert.assertEquals(SessionInputBufferMock.BUFFER_SIZE,inbuffer.available());
  Assert.assertEquals(0,inbuffer.length());
  inbuffer.read();
  Assert.assertEquals(SessionInputBufferMock.BUFFER_SIZE - 2,inbuffer.available());
  Assert.assertEquals(2,inbuffer.length());
  SessionOutputBufferMock outbuffer=new SessionOutputBufferMock();
  Assert.assertEquals(SessionOutputBufferMock.BUFFER_SIZE,outbuffer.capacity());
  Assert.assertEquals(SessionOutputBufferMock.BUFFER_SIZE,outbuffer.available());
  Assert.assertEquals(0,outbuffer.length());
  outbuffer.write(new byte[]{1,2,3});
  Assert.assertEquals(SessionOutputBufferMock.BUFFER_SIZE - 3,outbuffer.available());
  Assert.assertEquals(3,outbuffer.length());
}
