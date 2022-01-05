@Test public void red() throws Exception {
  this.converter.setOptionList(Collections.singletonList("red"));
  String out=this.converter.transform(this.event,this.in);
  assertThat(out,equalTo("\033[31min\033[0;39m"));
}
