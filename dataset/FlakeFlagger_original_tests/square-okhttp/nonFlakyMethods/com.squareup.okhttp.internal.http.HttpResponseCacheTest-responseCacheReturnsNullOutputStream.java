/** 
 * Don't explode if the cache returns a null body. http://b/3373699 
 */
@Test public void responseCacheReturnsNullOutputStream() throws Exception {
  final AtomicBoolean aborted=new AtomicBoolean();
  client.setOkResponseCache(new AbstractOkResponseCache(){
    @Override public CacheRequest put(    Response response) throws IOException {
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
  assertEquals("abc",readAscii(connection,3));
  connection.getInputStream().close();
  assertFalse(aborted.get());
}
