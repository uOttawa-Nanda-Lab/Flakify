@Test public void redirectWithRedirectsDisabled() throws Exception {
  client.setFollowProtocolRedirects(false);
  server.enqueue(new MockResponse().setResponseCode(301).addHeader("Location: /b").addHeader("Test","Redirect from /a to /b").setBody("/a has moved!"));
  server.play();
  Request request=new Request.Builder().url(server.getUrl("/a")).build();
  client.enqueue(request,receiver);
  receiver.await(server.getUrl("/a")).assertCode(301).assertBody("/a has moved!").assertContainsHeaders("Location: /b");
}
