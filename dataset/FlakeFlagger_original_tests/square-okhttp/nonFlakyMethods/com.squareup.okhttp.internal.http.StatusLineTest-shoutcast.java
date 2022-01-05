@Test public void shoutcast() throws IOException {
  StatusLine statusLine=new StatusLine("ICY 200 OK");
  assertEquals("OK",statusLine.message());
  assertEquals(0,statusLine.httpMinorVersion());
  assertEquals(200,statusLine.code());
}
