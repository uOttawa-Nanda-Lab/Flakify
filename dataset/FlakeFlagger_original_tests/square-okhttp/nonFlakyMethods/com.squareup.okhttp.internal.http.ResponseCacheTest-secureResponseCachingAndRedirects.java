@Test public void secureResponseCachingAndRedirects() throws IOException {
  server.useHttps(sslContext.getSocketFactory(),false);
  server.enqueue(new MockResponse().addHeader("Last-Modified: " + formatDate(-1,TimeUnit.HOURS)).addHeader("Expires: " + formatDate(1,TimeUnit.HOURS)).setResponseCode(HttpURLConnection.HTTP_MOVED_PERM).addHeader("Location: /foo"));
  server.enqueue(new MockResponse().addHeader("Last-Modified: " + formatDate(-1,TimeUnit.HOURS)).addHeader("Expires: " + formatDate(1,TimeUnit.HOURS)).setBody("ABC"));
  server.enqueue(new MockResponse().setBody("DEF"));
  server.play();
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(NULL_HOSTNAME_VERIFIER);
  HttpsURLConnection connection1=(HttpsURLConnection)openConnection(server.getUrl("/"));
  assertEquals("ABC",readAscii(connection1));
  assertNotNull(connection1.getCipherSuite());
  HttpsURLConnection connection2=(HttpsURLConnection)openConnection(server.getUrl("/"));
  assertEquals("ABC",readAscii(connection2));
  assertNotNull(connection2.getCipherSuite());
  assertEquals(connection1.getCipherSuite(),connection2.getCipherSuite());
}
