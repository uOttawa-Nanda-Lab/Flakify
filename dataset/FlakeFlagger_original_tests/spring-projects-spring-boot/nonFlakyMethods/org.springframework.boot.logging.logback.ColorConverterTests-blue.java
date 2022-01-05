@Test public void blue() throws Exception {
  this.converter.setOptionList(Collections.singletonList("blue"));
  String out=this.converter.transform(this.event,this.in);
  assertThat(out,equalTo("\033[34min\033[0;39m"));
}
