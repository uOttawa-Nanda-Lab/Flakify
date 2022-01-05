@Test public void responseCacheRequestHeaders() throws IOException, URISyntaxException {
  server.enqueue(new MockResponse().setBody("ABC"));
  server.play();
  final AtomicReference<Map<String,List<String>>> requestHeadersRef=new AtomicReference<Map<String,List<String>>>();
  client.setResponseCache(new ResponseCache(){
    @Override public CacheResponse get(    URI uri,    String requestMethod,    Map<String,List<String>> requestHeaders) throws IOException {
      requestHeadersRef.set(requestHeaders);
      return null;
    }
    @Override public CacheRequest put(    URI uri,    URLConnection conn) throws IOException {
      return null;
    }
  }
);
  URL url=server.getUrl("/");
  URLConnection urlConnection=openConnection(url);
  urlConnection.addRequestProperty("A","android");
  readAscii(urlConnection);
  assertEquals(Arrays.asList("android"),requestHeadersRef.get().get("A"));
}
