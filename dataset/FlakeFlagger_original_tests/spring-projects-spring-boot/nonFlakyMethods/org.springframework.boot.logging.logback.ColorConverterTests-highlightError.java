@Test public void highlightError() throws Exception {
  this.event.setLevel(Level.ERROR);
  String out=this.converter.transform(this.event,this.in);
  assertThat(out,equalTo("\033[31min\033[0;39m"));
}
