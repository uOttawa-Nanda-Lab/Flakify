@Test public void inspectHandshakeThroughoutRequestLifecycle() throws Exception {
  server.useHttps(sslContext.getSocketFactory(),false);
  server.enqueue(new MockResponse());
  server.play();
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(new RecordingHostnameVerifier());
  HttpsURLConnection httpsConnection=(HttpsURLConnection)client.open(server.getUrl("/foo"));
  try {
    httpsConnection.getCipherSuite();
    fail();
  }
 catch (  IllegalStateException expected) {
  }
  httpsConnection.connect();
  assertNotNull(httpsConnection.getCipherSuite());
  assertContent("",httpsConnection);
  assertNotNull(httpsConnection.getCipherSuite());
  httpsConnection.disconnect();
  assertNotNull(httpsConnection.getCipherSuite());
}
