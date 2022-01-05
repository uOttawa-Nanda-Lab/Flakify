@Test public void put_https_uriAndClass() throws Exception {
  final URL serverUrl=configureHttpsServer(new MockResponse());
  assertEquals("https",serverUrl.getProtocol());
  ResponseCache responseCache=new NoOpResponseCache(){
    @Override public CacheRequest put(    URI uri,    URLConnection urlConnection) throws IOException {
      assertTrue(urlConnection instanceof HttpsURLConnection);
      assertEquals(toUri(serverUrl),uri);
      assertEquals(serverUrl,urlConnection.getURL());
      return null;
    }
  }
;
  client.setResponseCache(responseCache);
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(NULL_HOSTNAME_VERIFIER);
  connection=client.open(serverUrl);
  executeGet(connection);
}
