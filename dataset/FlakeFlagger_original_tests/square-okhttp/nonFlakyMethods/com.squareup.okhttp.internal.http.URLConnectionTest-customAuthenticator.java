@Test public void customAuthenticator() throws Exception {
  MockResponse pleaseAuthenticate=new MockResponse().setResponseCode(401).addHeader("WWW-Authenticate: Basic realm=\"protected area\"").setBody("Please authenticate.");
  server.enqueue(pleaseAuthenticate);
  server.enqueue(new MockResponse().setBody("A"));
  server.play();
  Credential credential=Credential.basic("jesse","peanutbutter");
  RecordingOkAuthenticator authenticator=new RecordingOkAuthenticator(credential);
  client.setAuthenticator(authenticator);
  assertContent("A",client.open(server.getUrl("/private")));
  assertContainsNoneMatching(server.takeRequest().getHeaders(),"Authorization: .*");
  assertContains(server.takeRequest().getHeaders(),"Authorization: " + credential.getHeaderValue());
  assertEquals(1,authenticator.calls.size());
  String call=authenticator.calls.get(0);
  assertTrue(call,call.contains("proxy=DIRECT"));
  assertTrue(call,call.contains("url=" + server.getUrl("/private")));
  assertTrue(call,call.contains("challenges=[Basic realm=\"protected area\"]"));
}
