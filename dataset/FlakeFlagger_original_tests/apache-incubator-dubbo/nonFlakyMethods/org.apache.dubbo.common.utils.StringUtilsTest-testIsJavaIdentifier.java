@Test public void testIsJavaIdentifier() throws Exception {
  assertThat(StringUtils.isJavaIdentifier(""),is(false));
  assertThat(StringUtils.isJavaIdentifier("1"),is(false));
  assertThat(StringUtils.isJavaIdentifier("abc123"),is(true));
  assertThat(StringUtils.isJavaIdentifier("abc(23)"),is(false));
}
