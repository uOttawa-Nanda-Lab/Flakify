@Test public void contentDisagreesWithContentLengthHeader() throws IOException {
  server.enqueue(new MockResponse().setBody("abc\r\nYOU SHOULD NOT SEE THIS").clearHeaders().addHeader("Content-Length: 3"));
  server.play();
  assertContent("abc",client.open(server.getUrl("/")));
}
