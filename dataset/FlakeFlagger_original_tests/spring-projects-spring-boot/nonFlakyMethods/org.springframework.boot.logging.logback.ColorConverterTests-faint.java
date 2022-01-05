@Test public void faint() throws Exception {
  this.converter.setOptionList(Collections.singletonList("faint"));
  String out=this.converter.transform(this.event,this.in);
  assertThat(out,equalTo("\033[2min\033[0;39m"));
}
