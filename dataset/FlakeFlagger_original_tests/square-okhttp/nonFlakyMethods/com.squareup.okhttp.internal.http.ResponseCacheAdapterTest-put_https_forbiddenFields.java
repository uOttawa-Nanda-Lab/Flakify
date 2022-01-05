@Test public void put_https_forbiddenFields() throws Exception {
  final URL serverUrl=configureHttpsServer(new MockResponse());
  ResponseCache responseCache=new NoOpResponseCache(){
    @Override public CacheRequest put(    URI uri,    URLConnection urlConnection) throws IOException {
      HttpsURLConnection httpsUrlConnection=(HttpsURLConnection)urlConnection;
      try {
        httpsUrlConnection.getHostnameVerifier();
        fail();
      }
 catch (      UnsupportedOperationException expected) {
      }
      try {
        httpsUrlConnection.getSSLSocketFactory();
        fail();
      }
 catch (      UnsupportedOperationException expected) {
      }
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
