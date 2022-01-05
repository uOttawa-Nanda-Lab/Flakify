@Test public void testRepeat() throws Exception {
  assertThat(StringUtils.repeat(null,2),nullValue());
  assertThat(StringUtils.repeat("",0),equalTo(""));
  assertThat(StringUtils.repeat("",2),equalTo(""));
  assertThat(StringUtils.repeat("a",3),equalTo("aaa"));
  assertThat(StringUtils.repeat("ab",2),equalTo("abab"));
  assertThat(StringUtils.repeat("a",-2),equalTo(""));
  assertThat(StringUtils.repeat(null,null,2),nullValue());
  assertThat(StringUtils.repeat(null,"x",2),nullValue());
  assertThat(StringUtils.repeat("",null,0),equalTo(""));
  assertThat(StringUtils.repeat("","",2),equalTo(""));
  assertThat(StringUtils.repeat("","x",3),equalTo("xx"));
  assertThat(StringUtils.repeat("?",", ",3),equalTo("?, ?, ?"));
  assertThat(StringUtils.repeat('e',0),equalTo(""));
  assertThat(StringUtils.repeat('e',3),equalTo("eee"));
}
