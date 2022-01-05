@Test public void testChunkedInputStreamOneByteRead() throws IOException {
  String s="5\r\n01234\r\n5\r\n56789\r\n0\r\n";
  ChunkedInputStream in=new ChunkedInputStream(new SessionInputBufferMock(EncodingUtils.getBytes(s,CONTENT_CHARSET)));
  int ch;
  int i='0';
  while ((ch=in.read()) != -1) {
    Assert.assertEquals(i,ch);
    i++;
  }
  Assert.assertEquals(-1,in.read());
  Assert.assertEquals(-1,in.read());
  in.close();
}
