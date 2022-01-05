@Test public void sessionExpired() throws Exception {
  server.enqueue(new MockResponse().setResponseCode(422));
  HttpGet request=new HttpGet(server.getUrl("/").toURI());
  HttpResponse response=client.execute(request);
  assertEquals(422,response.getStatusLine().getStatusCode());
}
