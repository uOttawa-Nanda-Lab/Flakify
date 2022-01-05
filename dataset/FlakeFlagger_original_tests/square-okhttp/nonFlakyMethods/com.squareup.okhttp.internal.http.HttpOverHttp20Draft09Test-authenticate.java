@Test public void authenticate() throws Exception {
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED).addHeader("www-authenticate: Basic realm=\"protected area\"").setBody("Please authenticate."));
  server.enqueue(new MockResponse().setBody("Successful auth!"));
  server.play();
  Authenticator.setDefault(new RecordingAuthenticator());
  connection=client.open(server.getUrl("/"));
  assertEquals("Successful auth!",readAscii(connection.getInputStream(),Integer.MAX_VALUE));
  RecordedRequest denied=server.takeRequest();
  assertContainsNoneMatching(denied.getHeaders(),"authorization: Basic .*");
  RecordedRequest accepted=server.takeRequest();
  assertEquals("GET / HTTP/1.1",accepted.getRequestLine());
  assertContains(accepted.getHeaders(),"authorization: Basic " + RecordingAuthenticator.BASE_64_CREDENTIALS);
}
