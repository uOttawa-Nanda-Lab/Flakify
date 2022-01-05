@Test public void emptyMessage() throws IOException {
  int version=1;
  int code=503;
  StatusLine statusLine=new StatusLine("HTTP/1." + version + " "+ code+ " ");
  assertEquals("",statusLine.message());
  assertEquals(version,statusLine.httpMinorVersion());
  assertEquals(code,statusLine.code());
}
