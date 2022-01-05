@Test public void put_uriAndClass() throws Exception {
  final URL serverUrl=configureServer(new MockResponse());
  ResponseCache responseCache=new NoOpResponseCache(){
    @Override public CacheRequest put(    URI uri,    URLConnection urlConnection) throws IOException {
      assertTrue(urlConnection instanceof HttpURLConnection);
      assertFalse(urlConnection instanceof HttpsURLConnection);
      assertEquals(toUri(serverUrl),uri);
      assertEquals(serverUrl,urlConnection.getURL());
      return null;
    }
  }
;
  client.setResponseCache(responseCache);
  connection=client.open(serverUrl);
  executeGet(connection);
}
