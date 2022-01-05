@Test public void sourceFromInputStreamBounds() throws Exception {
  Source source=Okio.source(new ByteArrayInputStream(new byte[100]));
  try {
    source.read(new OkBuffer(),-1);
    fail();
  }
 catch (  IllegalArgumentException expected) {
  }
}
