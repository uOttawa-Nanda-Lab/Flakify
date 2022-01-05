@Test public void put_responseChangesForbidden() throws Exception {
  final URL serverUrl=configureServer(new MockResponse());
  ResponseCache responseCache=new NoOpResponseCache(){
    @Override public CacheRequest put(    URI uri,    URLConnection urlConnection) throws IOException {
      HttpURLConnection httpUrlConnection=(HttpURLConnection)urlConnection;
      try {
        httpUrlConnection.getInputStream();
        fail();
      }
 catch (      UnsupportedOperationException expected) {
      }
      try {
        httpUrlConnection.getContent();
        fail();
      }
 catch (      UnsupportedOperationException expected) {
      }
      try {
        httpUrlConnection.setFixedLengthStreamingMode(1234);
        fail();
      }
 catch (      UnsupportedOperationException expected) {
      }
      try {
        httpUrlConnection.setRequestMethod("PUT");
        fail();
      }
 catch (      UnsupportedOperationException expected) {
      }
      try {
        urlConnection.getHeaderFields().put("key",Collections.singletonList("value"));
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
