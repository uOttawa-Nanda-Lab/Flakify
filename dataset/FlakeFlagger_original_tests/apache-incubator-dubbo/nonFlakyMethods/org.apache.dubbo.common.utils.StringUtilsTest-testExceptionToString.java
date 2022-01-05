@Test public void testExceptionToString() throws Exception {
  assertThat(StringUtils.toString(new RuntimeException("abc")),containsString("java.lang.RuntimeException: abc"));
}
