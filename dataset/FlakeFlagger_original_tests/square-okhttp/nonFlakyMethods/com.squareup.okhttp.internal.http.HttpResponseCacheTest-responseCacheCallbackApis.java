/** 
 * Test that we can interrogate the response when the cache is being populated. http://code.google.com/p/android/issues/detail?id=7787
 */
@Test public void responseCacheCallbackApis() throws Exception {
  final String body="ABCDE";
  final AtomicInteger cacheCount=new AtomicInteger();
  server.enqueue(new MockResponse().setStatus("HTTP/1.1 200 Fantastic").addHeader("Content-Type: text/plain").addHeader("fgh: ijk").setBody(body));
  server.play();
  client.setOkResponseCache(new AbstractOkResponseCache(){
    @Override public CacheRequest put(    Response response) throws IOException {
      assertEquals(server.getUrl("/"),response.request().url());
      assertEquals(200,response.code());
      assertNull(response.body());
      assertEquals("5",response.header("Content-Length"));
      assertEquals("text/plain",response.header("Content-Type"));
      assertEquals("ijk",response.header("fgh"));
      cacheCount.incrementAndGet();
      return null;
    }
  }
);
  URL url=server.getUrl("/");
  HttpURLConnection connection=openConnection(url);
  assertEquals(body,readAscii(connection));
  assertEquals(1,cacheCount.get());
}
