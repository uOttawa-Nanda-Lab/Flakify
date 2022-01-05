@Test public void testMalformedCharacters() throws Exception {
  HttpParams params=new BasicHttpParams();
  String s1=constructString(SWISS_GERMAN_HELLO);
  SessionOutputBuffer outbuf=new SessionOutputBufferImpl(1024,16,params);
  try {
    outbuf.writeLine(s1);
    Assert.fail("Expected CharacterCodingException");
  }
 catch (  CharacterCodingException expected) {
  }
  byte[] tmp=s1.getBytes("ISO-8859-1");
  ReadableByteChannel channel=newChannel(tmp);
  SessionInputBuffer inbuf=new SessionInputBufferImpl(16,16,params);
  while (inbuf.fill(channel) > 0) {
  }
  try {
    String s=inbuf.readLine(true);
    Assert.fail("Expected CharacterCodingException, got '" + s + "'");
  }
 catch (  CharacterCodingException expected) {
  }
}
