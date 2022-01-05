@Test public void testConstructors() throws Exception {
  try {
    new ChunkedInputStream((SessionInputBuffer)null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  new MalformedChunkCodingException();
  new MalformedChunkCodingException("");
}
