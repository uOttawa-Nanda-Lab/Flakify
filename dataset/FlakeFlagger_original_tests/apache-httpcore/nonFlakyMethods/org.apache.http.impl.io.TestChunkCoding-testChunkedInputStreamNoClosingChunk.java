@Test public void testChunkedInputStreamNoClosingChunk() throws IOException {
  String s="5\r\n01234\r\n";
  ChunkedInputStream in=new ChunkedInputStream(new SessionInputBufferMock(EncodingUtils.getBytes(s,CONTENT_CHARSET)));
  byte[] tmp=new byte[5];
  Assert.assertEquals(5,in.read(tmp));
  Assert.assertEquals(-1,in.read());
}
