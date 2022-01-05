/** 
 * Don't explode if the cache returns a null body. http://b/3373699 
 */
@Test public void responseCacheReturnsNullOutputStream() throws Exception {
  final AtomicBoolean aborted=new AtomicBoolean();
  client.setResponseCache(new ResponseCache(){
    @Override public CacheResponse get(    URI uri,    String requestMethod,    Map<String,List<String>> requestHeaders) throws IOException {
      return null;
    }
    @Override public CacheRequest put(    URI uri,    URLConnection connection) throws IOException {
      return new CacheRequest(){
        @Override public void abort(){
          aborted.set(true);
        }
        @Override public OutputStream getBody() throws IOException {
          return null;
        }
      }
;
    }
  }
);
  server.enqueue(new MockResponse().setBody("abcdef"));
  server.play();
  HttpURLConnection connection=client.open(server.getUrl("/"));
  InputStream in=connection.getInputStream();
  assertEquals("abc",readAscii(in,3));
  in.close();
  assertFalse(aborted.get());
}
