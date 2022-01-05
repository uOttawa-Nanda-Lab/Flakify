@Test public void smoke() throws IOException {
  init(baos);
  String msg="hello";
  ILoggingEvent event=makeLoggingEvent(msg);
  ple.doEncode(event);
  ple.close();
  assertEquals(msg,baos.toString());
}
