@Test public void magenta() throws Exception {
  this.converter.setOptionList(Collections.singletonList("magenta"));
  String out=this.converter.transform(this.event,this.in);
  assertThat(out,equalTo("\033[35min\033[0;39m"));
}
