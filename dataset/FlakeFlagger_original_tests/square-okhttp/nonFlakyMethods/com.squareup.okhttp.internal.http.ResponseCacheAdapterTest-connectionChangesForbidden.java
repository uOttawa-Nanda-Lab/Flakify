@Test public void connectionChangesForbidden() throws Exception {
  final URL serverUrl=configureServer(new MockResponse());
  ResponseCache responseCache=new NoOpResponseCache(){
    @Override public CacheRequest put(    URI uri,    URLConnection urlConnection) throws IOException {
      HttpURLConnection httpUrlConnection=(HttpURLConnection)urlConnection;
      try {
        httpUrlConnection.connect();
        fail();
      }
 catch (      UnsupportedOperationException expected) {
      }
      try {
        httpUrlConnection.disconnect();
        fail();
      }
 catch (      UnsupportedOperationException expected) {
      }
      return null;
    }
  }
;
  client.setResponseCache(responseCache);
  connection=client.open(serverUrl);
  executeGet(connection);
}
