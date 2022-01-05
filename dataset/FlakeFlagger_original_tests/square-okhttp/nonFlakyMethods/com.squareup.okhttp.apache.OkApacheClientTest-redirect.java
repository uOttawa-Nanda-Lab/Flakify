@Test public void redirect() throws Exception {
  server.enqueue(new MockResponse().setResponseCode(302).addHeader("Location","/foo"));
  server.enqueue(new MockResponse().setBody("Hello, Redirect!"));
  HttpGet request=new HttpGet(server.getUrl("/").toURI());
  HttpResponse response=client.execute(request);
  String actual=EntityUtils.toString(response.getEntity());
  assertEquals("Hello, Redirect!",actual);
}
