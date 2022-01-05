@Test public void cyan() throws Exception {
  this.converter.setOptionList(Collections.singletonList("cyan"));
  String out=this.converter.transform(this.event,this.in);
  assertThat(out,equalTo("\033[36min\033[0;39m"));
}
