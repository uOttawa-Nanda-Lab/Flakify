@Test public void responseCacheRequestHeaders() throws IOException, URISyntaxException {
  server.enqueue(new MockResponse().setBody("ABC"));
  server.play();
  final AtomicReference<Request> requestRef=new AtomicReference<Request>();
  client.setOkResponseCache(new AbstractOkResponseCache(){
    @Override public Response get(    Request request) throws IOException {
      requestRef.set(request);
      return null;
    }
  }
);
  URL url=server.getUrl("/");
  URLConnection urlConnection=openConnection(url);
  urlConnection.addRequestProperty("A","android");
  readAscii(urlConnection);
  assertEquals(Arrays.asList("android"),requestRef.get().headers("A"));
}
