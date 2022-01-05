@Test public void setInvalidRequestMethodConnect() throws Exception {
  server.play();
  assertInvalidRequestMethod("CONNECT");
}
