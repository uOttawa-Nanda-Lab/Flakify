@Test public void yellow() throws Exception {
  this.converter.setOptionList(Collections.singletonList("yellow"));
  String out=this.converter.transform(this.event,this.in);
  assertThat(out,equalTo("\033[33min\033[0;39m"));
}
