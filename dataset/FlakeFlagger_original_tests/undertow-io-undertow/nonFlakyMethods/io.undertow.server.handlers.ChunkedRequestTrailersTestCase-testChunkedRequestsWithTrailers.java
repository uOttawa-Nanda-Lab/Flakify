/** 
 * We send our request manually, as apache HTTP client does not support this.
 * @throws IOException
 */
@Test public void testChunkedRequestsWithTrailers() throws IOException {
  connection=null;
  String request="POST / HTTP/1.1\r\nTrailer:foo, bar\r\nTransfer-Encoding: chunked\r\n\r\n9\r\nabcdefghi\r\n0\r\nfoo: fooVal\r\n bar: barVal\r\n\r\n";
  String response1="HTTP/1.1 200 OK\r\nConnection: keep-alive\r\nContent-Length: 26\r\n\r\nfoo: fooVal\r\nbar: barVal\r\n";
  String response2="HTTP/1.1 200 OK\r\nConnection: keep-alive\r\nContent-Length: 26\r\n\r\nfoo: fooVal\r\nbar: barVal\r\n";
  Socket s=new Socket(DefaultServer.getDefaultServerAddress().getAddress(),DefaultServer.getDefaultServerAddress().getPort());
  try {
    s.getOutputStream().write(request.getBytes());
    StringBuilder sb=new StringBuilder();
    int read=0;
    byte[] buf=new byte[100];
    while (read < response1.length()) {
      int r=s.getInputStream().read(buf);
      if (r <= 0)       break;
      if (r > 0) {
        read+=r;
        sb.append(new String(buf,0,r));
      }
    }
    try {
      Assert.assertEquals(response1,sb.toString());
    }
 catch (    AssertionError e) {
      Assert.assertEquals(response2,sb.toString());
    }
    s.getOutputStream().write(request.getBytes());
    sb=new StringBuilder();
    read=0;
    buf=new byte[100];
    while (read < response1.length()) {
      int r=s.getInputStream().read(buf);
      if (r <= 0)       break;
      if (r > 0) {
        read+=r;
        sb.append(new String(buf,0,r));
      }
    }
    try {
      Assert.assertEquals(response1,sb.toString());
    }
 catch (    AssertionError e) {
      Assert.assertEquals(response2,sb.toString());
    }
  }
  finally {
    s.close();
  }
}
