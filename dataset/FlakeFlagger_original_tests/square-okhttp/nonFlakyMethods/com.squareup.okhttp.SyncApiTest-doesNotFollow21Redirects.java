@Test public void doesNotFollow21Redirects() throws Exception {
  for (int i=0; i < 21; i++) {
    server.enqueue(new MockResponse().setResponseCode(301).addHeader("Location: /" + (i + 1)).setBody("Redirecting to /" + (i + 1)));
  }
  server.play();
  try {
    client.execute(new Request.Builder().url(server.getUrl("/0")).build());
    fail();
  }
 catch (  IOException e) {
    assertEquals("Too many redirects: 21",e.getMessage());
  }
}
