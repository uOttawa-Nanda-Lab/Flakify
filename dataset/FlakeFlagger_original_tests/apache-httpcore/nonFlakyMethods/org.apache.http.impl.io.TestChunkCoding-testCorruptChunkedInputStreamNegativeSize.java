@Test public void testCorruptChunkedInputStreamNegativeSize() throws IOException {
  String s="-5\r\n01234\r\n5\r\n56789\r\n0\r\n";
  InputStream in=new ChunkedInputStream(new SessionInputBufferMock(EncodingUtils.getBytes(s,CONTENT_CHARSET)));
  try {
    in.read();
    Assert.fail("MalformedChunkCodingException should have been thrown");
  }
 catch (  MalformedChunkCodingException e) {
  }
}
