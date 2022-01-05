@Test public void follow20Redirects() throws Exception {
  for (int i=0; i < 20; i++) {
    server.enqueue(new MockResponse().setResponseCode(301).addHeader("Location: /" + (i + 1)).setBody("Redirecting to /" + (i + 1)));
  }
  server.enqueue(new MockResponse().setBody("Success!"));
  server.play();
  onSuccess(new Request.Builder().url(server.getUrl("/0")).build()).assertCode(200).assertBody("Success!");
}
