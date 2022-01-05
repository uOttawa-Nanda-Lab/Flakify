@Test public void noEntity() throws Exception {
  server.enqueue(new MockResponse());
  HttpPost post=new HttpPost(server.getUrl("/").toURI());
  client.execute(post);
}
