@Test public void charset() throws IOException {
  ple.setCharset(utf8Charset);
  init(baos);
  String msg="\u03b1";
  ILoggingEvent event=makeLoggingEvent(msg);
  ple.doEncode(event);
  ple.close();
  assertEquals(msg,new String(baos.toByteArray(),utf8Charset.name()));
}
