@Test public void testClosedCondition() throws Exception {
  SessionOutputBufferMock transmitter=new SessionOutputBufferMock();
  IdentityOutputStream outstream=new IdentityOutputStream(transmitter);
  outstream.close();
  outstream.close();
  try {
    byte[] tmp=new byte[2];
    outstream.write(tmp,0,tmp.length);
    Assert.fail("IOException should have been thrown");
  }
 catch (  IOException e) {
  }
  try {
    outstream.write('a');
    Assert.fail("IOException should have been thrown");
  }
 catch (  IOException e) {
  }
}
