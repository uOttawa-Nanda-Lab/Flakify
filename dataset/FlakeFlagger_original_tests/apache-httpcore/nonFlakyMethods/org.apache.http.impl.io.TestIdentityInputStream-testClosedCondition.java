@Test public void testClosedCondition() throws Exception {
  byte[] input=new byte[]{'a','b','c'};
  SessionInputBufferMock receiver=new SessionInputBufferMock(input);
  IdentityInputStream instream=new IdentityInputStream(receiver);
  instream.close();
  instream.close();
  Assert.assertEquals(0,instream.available());
  byte[] tmp=new byte[2];
  Assert.assertEquals(-1,instream.read(tmp,0,tmp.length));
  Assert.assertEquals(-1,instream.read());
  Assert.assertEquals(-1,instream.read(tmp,0,tmp.length));
  Assert.assertEquals(-1,instream.read());
}
