@Test public void green() throws Exception {
  this.converter.setOptionList(Collections.singletonList("green"));
  String out=this.converter.transform(this.event,this.in);
  assertThat(out,equalTo("\033[32min\033[0;39m"));
}
