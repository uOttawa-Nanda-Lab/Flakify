@Test public void testConstructor() throws Exception {
  SessionOutputBufferMock transmitter=new SessionOutputBufferMock();
  new IdentityOutputStream(transmitter);
  try {
    new IdentityOutputStream(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
