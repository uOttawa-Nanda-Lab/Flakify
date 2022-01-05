@Test public void parse() throws IOException {
  String message="Temporary Redirect";
  int version=1;
  int code=200;
  StatusLine statusLine=new StatusLine("HTTP/1." + version + " "+ code+ " "+ message);
  assertEquals(message,statusLine.message());
  assertEquals(version,statusLine.httpMinorVersion());
  assertEquals(code,statusLine.code());
}
