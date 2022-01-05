@Test public void highlightWarn() throws Exception {
  this.event.setLevel(Level.WARN);
  String out=this.converter.transform(this.event,this.in);
  assertThat(out,equalTo("\033[33min\033[0;39m"));
}
