@Test public void testResumeOnSocketTimeoutInData() throws IOException {
  String s="5\r\n01234\r\n5\r\n5\0006789\r\na\r\n0123\000456789\r\n0\r\n";
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
  Assert.assertEquals(2,timeouts);
}
