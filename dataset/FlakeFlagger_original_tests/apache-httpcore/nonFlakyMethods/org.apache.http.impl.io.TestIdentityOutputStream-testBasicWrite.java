@Test public void testBasicWrite() throws Exception {
  SessionOutputBufferMock transmitter=new SessionOutputBufferMock();
  IdentityOutputStream outstream=new IdentityOutputStream(transmitter);
  outstream.write(new byte[]{'a','b'},0,2);
  outstream.write('c');
  outstream.flush();
  byte[] input=transmitter.getData();
  Assert.assertNotNull(input);
  byte[] expected=new byte[]{'a','b','c'};
  Assert.assertEquals(expected.length,input.length);
  for (int i=0; i < expected.length; i++) {
    Assert.assertEquals(expected[i],input[i]);
  }
}
