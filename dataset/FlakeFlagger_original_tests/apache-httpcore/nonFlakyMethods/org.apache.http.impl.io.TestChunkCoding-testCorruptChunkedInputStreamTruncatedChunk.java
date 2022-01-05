@Test public void testCorruptChunkedInputStreamTruncatedChunk() throws IOException {
  String s="3\r\n12";
  InputStream in=new ChunkedInputStream(new SessionInputBufferMock(EncodingUtils.getBytes(s,CONTENT_CHARSET)));
  byte[] buffer=new byte[300];
  Assert.assertEquals(2,in.read(buffer));
  try {
    in.read(buffer);
    Assert.fail("MalformedChunkCodingException should have been thrown");
  }
 catch (  MalformedChunkCodingException e) {
  }
}
