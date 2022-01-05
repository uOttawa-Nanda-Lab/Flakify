@Test public void put_responseHeadersOk() throws Exception {
  final String statusLine="HTTP/1.1 200 Fantastic";
  final URL serverUrl=configureServer(new MockResponse().setStatus(statusLine).addHeader("A","c").addHeader("B","d").addHeader("A","e"));
  ResponseCache responseCache=new NoOpResponseCache(){
    @Override public CacheRequest put(    URI uri,    URLConnection urlConnection) throws IOException {
      HttpURLConnection httpUrlConnection=(HttpURLConnection)urlConnection;
      assertEquals(200,httpUrlConnection.getResponseCode());
      assertEquals("Fantastic",httpUrlConnection.getResponseMessage());
      assertEquals(0,urlConnection.getContentLength());
      assertEquals(statusLine,httpUrlConnection.getHeaderField(null));
      assertEquals("e",httpUrlConnection.getHeaderField("A"));
      assertEquals("e",httpUrlConnection.getHeaderField("a"));
      Map<String,List<String>> responseHeaders=httpUrlConnection.getHeaderFields();
      assertEquals(Arrays.asList(statusLine),responseHeaders.get(null));
      assertEquals(newSet("c","e"),new HashSet<String>(responseHeaders.get("A")));
      assertEquals(newSet("c","e"),new HashSet<String>(responseHeaders.get("a")));
      assertHeadersContainsMapping(responseHeaders,null,statusLine);
      assertHeadersContainsMapping(responseHeaders,"A","c","e");
      assertHeadersContainsMapping(responseHeaders,"B","d");
      try {
        responseHeaders.put("N",Arrays.asList("o"));
        fail("Modified an unmodifiable view.");
      }
 catch (      UnsupportedOperationException expected) {
      }
      try {
        responseHeaders.get("A").add("f");
        fail("Modified an unmodifiable view.");
      }
 catch (      UnsupportedOperationException expected) {
      }
      assertEquals(null,httpUrlConnection.getHeaderFieldKey(0));
      assertEquals(statusLine,httpUrlConnection.getHeaderField(0));
      int i=1;
      while (!httpUrlConnection.getHeaderFieldKey(i).equals("A")) {
        i++;
      }
      assertResponseHeaderAtIndex(httpUrlConnection,i++,"A","c");
      assertResponseHeaderAtIndex(httpUrlConnection,i++,"B","d");
      assertResponseHeaderAtIndex(httpUrlConnection,i++,"A","e");
      while (httpUrlConnection.getHeaderField(i) != null) {
        assertNotNull(httpUrlConnection.getHeaderFieldKey(i));
        i++;
      }
      assertNull(httpUrlConnection.getHeaderFieldKey(i));
      return null;
    }
  }
;
  client.setResponseCache(responseCache);
  connection=client.open(serverUrl);
  executeGet(connection);
}
