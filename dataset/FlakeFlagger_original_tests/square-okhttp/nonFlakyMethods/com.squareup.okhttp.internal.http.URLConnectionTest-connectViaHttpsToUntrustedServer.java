/** 
 * Verify that we don't retry connections on certificate verification errors. http://code.google.com/p/android/issues/detail?id=13178
 */
@Test public void connectViaHttpsToUntrustedServer() throws IOException, InterruptedException {
  server.useHttps(sslContext.getSocketFactory(),false);
  server.enqueue(new MockResponse());
  server.play();
  connection=client.open(server.getUrl("/foo"));
  try {
    connection.getInputStream();
    fail();
  }
 catch (  SSLHandshakeException expected) {
    assertTrue(expected.getCause() instanceof CertificateException);
  }
  assertEquals(0,server.getRequestCount());
}
