@Test public void testClose() throws Exception {
  ByteArrayOutputStream buffer=new ByteArrayOutputStream();
  SessionOutputBufferMock datatransmitter=new SessionOutputBufferMock(buffer);
  OutputStream out=new IdentityOutputStream(datatransmitter);
  out.close();
  out.close();
  byte[] tmp=new byte[10];
  try {
    out.write(tmp);
    Assert.fail("IOException should have been thrown");
  }
 catch (  IOException ex) {
  }
  try {
    out.write(1);
    Assert.fail("IOException should have been thrown");
  }
 catch (  IOException ex) {
  }
}
