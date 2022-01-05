@Test public void testResumeOnSocketTimeoutInChunk() throws IOException {
  String s="5\000\r\000\n\00001234\r\n\0005\r\n56789\r\na\r\n0123456789\r\n\0000\r\n";
  SessionInputBuffer sessbuf=new SessionInputBufferMock(new TimeoutByteArrayInputStream(s.getBytes("ISO-8859-1")),16);
  InputStream in=new ChunkedInputStream(sessbuf);
  byte[] tmp=new byte[3];
  int bytesRead=0;
  int timeouts=0;
  int i=0;
  while (i != -1) {
    try {
      i=in.read(tmp);
      if (i > 0) {
        bytesRead+=i;
      }
    }
 catch (    InterruptedIOException ex) {
      timeouts++;
    }
  }
  Assert.assertEquals(20,bytesRead);
  Assert.assertEquals(5,timeouts);
}
