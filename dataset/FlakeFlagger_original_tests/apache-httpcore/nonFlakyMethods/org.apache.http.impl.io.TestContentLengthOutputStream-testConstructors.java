@Test public void testConstructors() throws Exception {
  new ContentLengthOutputStream(new SessionOutputBufferMock(),10L);
  try {
    new ContentLengthOutputStream(null,10L);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new ContentLengthOutputStream(new SessionOutputBufferMock(),-10);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
