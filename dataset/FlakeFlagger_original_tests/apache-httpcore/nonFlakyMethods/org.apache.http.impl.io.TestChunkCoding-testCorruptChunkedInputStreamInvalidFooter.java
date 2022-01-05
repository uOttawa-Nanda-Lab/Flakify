@Test public void testCorruptChunkedInputStreamInvalidFooter() throws IOException {
  String s="1\r\n0\r\n0\r\nstuff\r\n";
  InputStream in=new ChunkedInputStream(new SessionInputBufferMock(EncodingUtils.getBytes(s,CONTENT_CHARSET)));
  try {
    in.read();
    in.read();
    Assert.fail("MalformedChunkCodingException should have been thrown");
  }
 catch (  MalformedChunkCodingException e) {
  }
}
