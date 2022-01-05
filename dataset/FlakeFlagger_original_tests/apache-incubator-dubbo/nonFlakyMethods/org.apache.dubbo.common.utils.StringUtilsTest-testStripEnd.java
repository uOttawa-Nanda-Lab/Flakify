@Test public void testStripEnd() throws Exception {
  assertThat(StringUtils.stripEnd(null,"*"),nullValue());
  assertThat(StringUtils.stripEnd("",null),equalTo(""));
  assertThat(StringUtils.stripEnd("abc",""),equalTo("abc"));
  assertThat(StringUtils.stripEnd("abc",null),equalTo("abc"));
  assertThat(StringUtils.stripEnd("  abc",null),equalTo("  abc"));
  assertThat(StringUtils.stripEnd("abc  ",null),equalTo("abc"));
  assertThat(StringUtils.stripEnd(" abc ",null),equalTo(" abc"));
  assertThat(StringUtils.stripEnd("  abcyx","xyz"),equalTo("  abc"));
  assertThat(StringUtils.stripEnd("120.00",".0"),equalTo("12"));
}
