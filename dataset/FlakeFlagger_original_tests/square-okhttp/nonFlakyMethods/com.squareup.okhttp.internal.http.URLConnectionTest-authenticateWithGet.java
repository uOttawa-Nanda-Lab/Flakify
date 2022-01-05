@Test public void authenticateWithGet() throws Exception {
  MockResponse pleaseAuthenticate=new MockResponse().setResponseCode(401).addHeader("WWW-Authenticate: Basic realm=\"protected area\"").setBody("Please authenticate.");
  server.enqueue(pleaseAuthenticate);
  server.enqueue(pleaseAuthenticate);
  server.enqueue(pleaseAuthenticate);
  server.enqueue(new MockResponse().setBody("Successful auth!"));
  server.play();
  Authenticator.setDefault(new RecordingAuthenticator());
  connection=client.open(server.getUrl("/"));
  assertEquals("Successful auth!",readAscii(connection.getInputStream(),Integer.MAX_VALUE));
  RecordedRequest request=server.takeRequest();
  assertContainsNoneMatching(request.getHeaders(),"Authorization: Basic .*");
  for (int i=0; i < 3; i++) {
    request=server.takeRequest();
    assertEquals("GET / HTTP/1.1",request.getRequestLine());
    assertContains(request.getHeaders(),"Authorization: Basic " + RecordingAuthenticator.BASE_64_CREDENTIALS);
  }
}
