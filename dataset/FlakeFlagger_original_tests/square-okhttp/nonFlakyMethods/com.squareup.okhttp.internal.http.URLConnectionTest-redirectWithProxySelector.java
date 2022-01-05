@Test public void redirectWithProxySelector() throws Exception {
  final List<URI> proxySelectionRequests=new ArrayList<URI>();
  client.setProxySelector(new ProxySelector(){
    @Override public List<Proxy> select(    URI uri){
      proxySelectionRequests.add(uri);
      MockWebServer proxyServer=(uri.getPort() == server.getPort()) ? server : server2;
      return Arrays.asList(proxyServer.toProxyAddress());
    }
    @Override public void connectFailed(    URI uri,    SocketAddress address,    IOException failure){
      throw new AssertionError();
    }
  }
);
  server2=new MockWebServer();
  server2.enqueue(new MockResponse().setBody("This is the 2nd server!"));
  server2.play();
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_MOVED_TEMP).addHeader("Location: " + server2.getUrl("/b").toString()).setBody("This page has moved!"));
  server.play();
  assertContent("This is the 2nd server!",client.open(server.getUrl("/a")));
  assertEquals(Arrays.asList(server.getUrl("/a").toURI(),server2.getUrl("/b").toURI()),proxySelectionRequests);
  server2.shutdown();
}
