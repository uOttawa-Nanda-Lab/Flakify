@Test public void testAvailable() throws Exception {
  byte[] input=new byte[]{'a','b','c'};
  SessionInputBufferMock receiver=new SessionInputBufferMock(input);
  IdentityInputStream instream=new IdentityInputStream(receiver);
  instream.read();
  Assert.assertEquals(2,instream.available());
}
