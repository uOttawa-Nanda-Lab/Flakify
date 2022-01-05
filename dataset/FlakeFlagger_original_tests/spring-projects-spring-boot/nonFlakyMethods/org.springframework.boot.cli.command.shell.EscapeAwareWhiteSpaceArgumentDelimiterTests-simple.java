@Test public void simple() throws Exception {
  String s="one two";
  assertThat(this.delimiter.delimit(s,0).getArguments(),equalTo(new String[]{"one","two"}));
  assertThat(this.delimiter.parseArguments(s),equalTo(new String[]{"one","two"}));
  assertThat(this.delimiter.isDelimiter(s,2),equalTo(false));
  assertThat(this.delimiter.isDelimiter(s,3),equalTo(true));
  assertThat(this.delimiter.isDelimiter(s,4),equalTo(false));
}
