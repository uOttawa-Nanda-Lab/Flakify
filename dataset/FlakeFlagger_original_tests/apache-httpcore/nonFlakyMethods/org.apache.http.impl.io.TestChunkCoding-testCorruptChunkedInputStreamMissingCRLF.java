@Test public void testCorruptChunkedInputStreamMissingCRLF() throws IOException {
  String s="5\r\n012345\r\n56789\r\n0\r\n";
  InputStream in=new ChunkedInputStream(new SessionInputBufferMock(EncodingUtils.getBytes(s,CONTENT_CHARSET)));
  byte[] buffer=new byte[300];
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  int len;
  try {
    while ((len=in.read(buffer)) > 0) {
      out.write(buffer,0,len);
    }
    Assert.fail("MalformedChunkCodingException should have been thrown");
  }
 catch (  MalformedChunkCodingException e) {
  }
}
