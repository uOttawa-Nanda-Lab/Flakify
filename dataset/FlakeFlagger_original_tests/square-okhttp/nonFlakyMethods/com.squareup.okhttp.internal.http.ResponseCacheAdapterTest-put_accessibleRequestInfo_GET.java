@Test public void put_accessibleRequestInfo_GET() throws Exception {
  final URL serverUrl=configureServer(new MockResponse());
  ResponseCache responseCache=new NoOpResponseCache(){
    @Override public CacheRequest put(    URI uri,    URLConnection urlConnection) throws IOException {
      HttpURLConnection httpUrlConnection=(HttpURLConnection)urlConnection;
      assertEquals("GET",httpUrlConnection.getRequestMethod());
      assertTrue(httpUrlConnection.getDoInput());
      assertFalse(httpUrlConnection.getDoOutput());
      return null;
    }
  }
;
  client.setResponseCache(responseCache);
  connection=client.open(serverUrl);
  executeGet(connection);
}
