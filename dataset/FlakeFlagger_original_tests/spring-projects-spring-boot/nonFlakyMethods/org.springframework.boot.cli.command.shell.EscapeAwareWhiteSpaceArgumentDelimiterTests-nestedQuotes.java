@Test public void nestedQuotes() throws Exception {
  String s="\"o 'n''e\" 't \"w o'";
  assertThat(this.delimiter.delimit(s,0).getArguments(),equalTo(new String[]{"\"o 'n''e\"","'t \"w o'"}));
  assertThat(this.delimiter.parseArguments(s),equalTo(new String[]{"o 'n''e","t \"w o"}));
}
