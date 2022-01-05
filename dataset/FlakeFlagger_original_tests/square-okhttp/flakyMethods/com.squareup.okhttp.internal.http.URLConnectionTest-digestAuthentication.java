@Test public void digestAuthentication() throws Exception {
  List<String> calls=authCallsForHeader("WWW-Authenticate: Digest " + "realm=\"testrealm@host.com\", qop=\"auth,auth-int\", " + "nonce=\"dcd98b7102dd2f0e8b11d0f600bfb0c093\", "+ "opaque=\"5ccc069c403ebaf9f0171e9517f40e41\"");
  assertEquals(0,calls.size());
}
