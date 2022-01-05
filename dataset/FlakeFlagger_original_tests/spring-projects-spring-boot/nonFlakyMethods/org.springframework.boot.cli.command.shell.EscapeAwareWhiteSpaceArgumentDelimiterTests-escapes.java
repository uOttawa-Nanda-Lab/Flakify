@Test public void escapes() throws Exception {
  String s="\\ \\\\.\\\\\\t";
  assertThat(this.delimiter.parseArguments(s),equalTo(new String[]{" \\.\\\t"}));
}
