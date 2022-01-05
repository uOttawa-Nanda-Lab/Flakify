@Test public void put_https_extraHttpsMethods() throws Exception {
  final URL serverUrl=configureHttpsServer(new MockResponse());
  assertEquals("https",serverUrl.getProtocol());
  ResponseCache responseCache=new NoOpResponseCache(){
    @Override public CacheRequest put(    URI uri,    URLConnection urlConnection) throws IOException {
      HttpsURLConnection cacheHttpsUrlConnection=(HttpsURLConnection)urlConnection;
      HttpsURLConnection realHttpsUrlConnection=(HttpsURLConnection)connection;
      assertEquals(realHttpsUrlConnection.getCipherSuite(),cacheHttpsUrlConnection.getCipherSuite());
      assertEquals(realHttpsUrlConnection.getPeerPrincipal(),cacheHttpsUrlConnection.getPeerPrincipal());
      assertArrayEquals(realHttpsUrlConnection.getLocalCertificates(),cacheHttpsUrlConnection.getLocalCertificates());
      assertArrayEquals(realHttpsUrlConnection.getServerCertificates(),cacheHttpsUrlConnection.getServerCertificates());
      assertEquals(realHttpsUrlConnection.getLocalPrincipal(),cacheHttpsUrlConnection.getLocalPrincipal());
      return null;
    }
  }
;
  client.setResponseCache(responseCache);
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(NULL_HOSTNAME_VERIFIER);
  connection=client.open(serverUrl);
  executeGet(connection);
  RecordedRequest recordedRequest=server.takeRequest();
  recordedRequest.getSslProtocol();
}
