@Test public void allAttributesSetInServerAuthenticationCallbacks() throws Exception {
  List<String> calls=authCallsForHeader("WWW-Authenticate: Basic realm=\"Bar\"");
  assertEquals(1,calls.size());
  URL url=server.getUrl("/");
  String call=calls.get(0);
  assertTrue(call,call.contains("host=" + url.getHost()));
  assertTrue(call,call.contains("port=" + url.getPort()));
  assertTrue(call,call.contains("site=" + InetAddress.getAllByName(url.getHost())[0]));
  assertTrue(call,call.contains("url=" + url));
  assertTrue(call,call.contains("type=" + Authenticator.RequestorType.SERVER));
  assertTrue(call,call.contains("prompt=Bar"));
  assertTrue(call,call.contains("protocol=http"));
  assertTrue(call,call.toLowerCase().contains("scheme=basic"));
}
