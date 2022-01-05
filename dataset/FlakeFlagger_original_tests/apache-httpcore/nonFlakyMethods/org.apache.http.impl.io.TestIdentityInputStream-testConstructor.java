@Test public void testConstructor() throws Exception {
  SessionInputBuffer receiver=new SessionInputBufferMock(new byte[]{});
  new IdentityInputStream(receiver);
  try {
    new IdentityInputStream(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
