@Test public void escapedQuotes() throws Exception {
  String s="\\'a b";
  ArgumentList argumentList=this.delimiter.delimit(s,0);
  assertThat(argumentList.getArguments(),equalTo(new String[]{"\\'a","b"}));
  assertThat(this.delimiter.parseArguments(s),equalTo(new String[]{"'a","b"}));
}
