@Test public void highlightTrace() throws Exception {
  this.event.setLevel(Level.TRACE);
  String out=this.converter.transform(this.event,this.in);
  assertThat(out,equalTo("\033[32min\033[0;39m"));
}
