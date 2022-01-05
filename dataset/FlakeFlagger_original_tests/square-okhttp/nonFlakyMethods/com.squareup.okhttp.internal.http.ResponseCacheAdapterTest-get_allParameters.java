@Test public void get_allParameters() throws Exception {
  final URL serverUrl=configureServer(new MockResponse());
  assertEquals("http",serverUrl.getProtocol());
  ResponseCache responseCache=new NoOpResponseCache(){
    @Override public CacheResponse get(    URI uri,    String method,    Map<String,List<String>> headers) throws IOException {
      assertEquals(toUri(serverUrl),uri);
      assertEquals("GET",method);
      assertTrue("Arbitrary standard header not present",headers.containsKey("User-Agent"));
      assertEquals(Collections.singletonList("value1"),headers.get("key1"));
      return null;
    }
  }
;
  client.setResponseCache(responseCache);
  connection=client.open(serverUrl);
  connection.setRequestProperty("key1","value1");
  executeGet(connection);
}
