@Test public void put_requestHeadersPartlyUnavailable() throws Exception {
  final URL serverUrl=configureServer(new MockResponse());
  ResponseCache responseCache=new NoOpResponseCache(){
    @Override public CacheRequest put(    URI uri,    URLConnection urlConnection) throws IOException {
      try {
        urlConnection.getRequestProperties();
        fail();
      }
 catch (      UnsupportedOperationException expected) {
      }
      assertEquals("value",urlConnection.getRequestProperty("key"));
      return null;
    }
  }
;
  client.setResponseCache(responseCache);
  connection=client.open(serverUrl);
  connection.setRequestProperty("key","value");
  executeGet(connection);
}
