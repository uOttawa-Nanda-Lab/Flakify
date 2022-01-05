@Test public void put_accessibleRequestInfo_POST() throws Exception {
  final URL serverUrl=configureServer(new MockResponse());
  ResponseCache responseCache=new NoOpResponseCache(){
    @Override public CacheRequest put(    URI uri,    URLConnection urlConnection) throws IOException {
      HttpURLConnection httpUrlConnection=(HttpURLConnection)urlConnection;
      assertEquals("POST",httpUrlConnection.getRequestMethod());
      assertTrue(httpUrlConnection.getDoInput());
      assertTrue(httpUrlConnection.getDoOutput());
      return null;
    }
  }
;
  client.setResponseCache(responseCache);
  connection=client.open(serverUrl);
  executePost(connection);
}
