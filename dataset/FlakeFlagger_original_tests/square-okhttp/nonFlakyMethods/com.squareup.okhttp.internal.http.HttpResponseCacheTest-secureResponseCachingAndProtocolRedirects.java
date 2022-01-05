/** 
 * We've had bugs where caching and cross-protocol redirects yield class cast exceptions internal to the cache because we incorrectly assumed that HttpsURLConnection was always HTTPS and HttpURLConnection was always HTTP; in practice redirects mean that each can do either. https://github.com/square/okhttp/issues/214
 */
@Test public void secureResponseCachingAndProtocolRedirects() throws IOException {
  server2.useHttps(sslContext.getSocketFactory(),false);
  server2.enqueue(new MockResponse().addHeader("Last-Modified: " + formatDate(-1,TimeUnit.HOURS)).addHeader("Expires: " + formatDate(1,TimeUnit.HOURS)).setBody("ABC"));
  server2.enqueue(new MockResponse().setBody("DEF"));
  server2.play();
  server.enqueue(new MockResponse().addHeader("Last-Modified: " + formatDate(-1,TimeUnit.HOURS)).addHeader("Expires: " + formatDate(1,TimeUnit.HOURS)).setResponseCode(HttpURLConnection.HTTP_MOVED_PERM).addHeader("Location: " + server2.getUrl("/")));
  server.play();
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(NULL_HOSTNAME_VERIFIER);
  HttpURLConnection connection1=client.open(server.getUrl("/"));
  assertEquals("ABC",readAscii(connection1));
  HttpURLConnection connection2=client.open(server.getUrl("/"));
  assertEquals("ABC",readAscii(connection2));
  assertEquals(4,cache.getRequestCount());
  assertEquals(2,cache.getHitCount());
}
