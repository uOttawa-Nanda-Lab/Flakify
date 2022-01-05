@Test public void postInputStreamEntity() throws Exception {
  server.enqueue(new MockResponse());
  final HttpPost post=new HttpPost(server.getUrl("/").toURI());
  byte[] body="Hello, world!".getBytes("UTF-8");
  post.setEntity(new InputStreamEntity(new ByteArrayInputStream(body),body.length));
  client.execute(post);
  RecordedRequest request=server.takeRequest();
  assertTrue(Arrays.equals(body,request.getBody()));
  assertEquals(request.getHeader("Content-Length"),"13");
}
