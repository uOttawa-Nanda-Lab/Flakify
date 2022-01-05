/** 
 * https://github.com/square/okhttp/issues/342 
 */
@Test public void authenticateRealmUppercase() throws Exception {
  server.enqueue(new MockResponse().setResponseCode(401).addHeader("wWw-aUtHeNtIcAtE: bAsIc rEaLm=\"pRoTeCtEd aReA\"").setBody("Please authenticate."));
  server.enqueue(new MockResponse().setBody("Successful auth!"));
  server.play();
  Authenticator.setDefault(new RecordingAuthenticator());
  connection=client.open(server.getUrl("/"));
  assertEquals("Successful auth!",readAscii(connection.getInputStream(),Integer.MAX_VALUE));
}
