@Test public void testConstructors() throws Exception {
  new ContentLengthInputStream(new SessionInputBufferMock(new byte[]{}),10);
  try {
    new ContentLengthInputStream(null,10);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new ContentLengthInputStream(new SessionInputBufferMock(new byte[]{}),-10);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
