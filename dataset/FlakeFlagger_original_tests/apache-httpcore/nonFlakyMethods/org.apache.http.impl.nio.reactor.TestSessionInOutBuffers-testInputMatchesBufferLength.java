@Test public void testInputMatchesBufferLength() throws Exception {
  HttpParams params=new BasicHttpParams();
  String s1="abcde";
  SessionOutputBuffer outbuf=new SessionOutputBufferImpl(1024,5,params);
  outbuf.writeLine(s1);
}
