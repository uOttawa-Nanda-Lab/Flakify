@Test public void withStackTrace() throws Exception {
  this.event.setThrowableProxy(new ThrowableProxy(new RuntimeException()));
  String s=this.converter.convert(this.event);
  assertThat(s,startsWith(LINE_SEPARATOR));
  assertThat(s,endsWith(LINE_SEPARATOR));
}
