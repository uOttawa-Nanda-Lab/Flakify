@Test public void nonStandardAuthenticationScheme() throws Exception {
  List<String> calls=authCallsForHeader("WWW-Authenticate: Foo");
  assertEquals(Collections.<String>emptyList(),calls);
}
