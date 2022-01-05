@Test public void noStackTrace() throws Exception {
  String s=this.converter.convert(this.event);
  assertThat(s,equalTo(""));
}
