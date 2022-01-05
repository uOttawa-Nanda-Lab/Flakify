@Test public void nonStandardAuthenticationSchemeWithRealm() throws Exception {
  List<String> calls=authCallsForHeader("WWW-Authenticate: Foo realm=\"Bar\"");
  assertEquals(0,calls.size());
}
