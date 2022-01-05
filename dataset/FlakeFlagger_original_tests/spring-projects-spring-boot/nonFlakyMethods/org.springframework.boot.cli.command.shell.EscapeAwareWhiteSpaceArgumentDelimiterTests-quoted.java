@Test public void quoted() throws Exception {
  String s="'o ne' 't w o'";
  assertThat(this.delimiter.delimit(s,0).getArguments(),equalTo(new String[]{"'o ne'","'t w o'"}));
  assertThat(this.delimiter.parseArguments(s),equalTo(new String[]{"o ne","t w o"}));
}
