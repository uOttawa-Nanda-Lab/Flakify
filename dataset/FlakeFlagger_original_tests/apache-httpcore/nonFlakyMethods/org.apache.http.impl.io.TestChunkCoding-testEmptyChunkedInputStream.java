@Test public void testEmptyChunkedInputStream() throws IOException {
  String input="0\r\n";
  InputStream in=new ChunkedInputStream(new SessionInputBufferMock(EncodingUtils.getBytes(input,CONTENT_CHARSET)));
  byte[] buffer=new byte[300];
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  int len;
  while ((len=in.read(buffer)) > 0) {
    out.write(buffer,0,len);
  }
  Assert.assertEquals(0,out.size());
}
