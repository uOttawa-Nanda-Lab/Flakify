@Test public void success() throws Exception {
  server.enqueue(new MockResponse().setBody("Hello, World!"));
  HttpGet request=new HttpGet(server.getUrl("/").toURI());
  HttpResponse response=client.execute(request);
  String actual=EntityUtils.toString(response.getEntity());
  assertEquals("Hello, World!",actual);
}
