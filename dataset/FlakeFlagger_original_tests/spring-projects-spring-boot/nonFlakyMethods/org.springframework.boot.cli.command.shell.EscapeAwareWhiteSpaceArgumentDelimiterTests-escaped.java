@Test public void escaped() throws Exception {
  String s="o\\ ne two";
  assertThat(this.delimiter.delimit(s,0).getArguments(),equalTo(new String[]{"o\\ ne","two"}));
  assertThat(this.delimiter.parseArguments(s),equalTo(new String[]{"o ne","two"}));
  assertThat(this.delimiter.isDelimiter(s,2),equalTo(false));
  assertThat(this.delimiter.isDelimiter(s,3),equalTo(false));
  assertThat(this.delimiter.isDelimiter(s,4),equalTo(false));
  assertThat(this.delimiter.isDelimiter(s,5),equalTo(true));
}
