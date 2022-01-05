@Test public void testExceptionToStringWithMessage() throws Exception {
  String s=StringUtils.toString("greeting",new RuntimeException("abc"));
  assertThat(s,containsString("greeting"));
  assertThat(s,containsString("java.lang.RuntimeException: abc"));
}
