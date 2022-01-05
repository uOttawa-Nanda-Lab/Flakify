@Test public void highlightDebug() throws Exception {
  this.event.setLevel(Level.DEBUG);
  String out=this.converter.transform(this.event,this.in);
  assertThat(out,equalTo("\033[32min\033[0;39m"));
}
