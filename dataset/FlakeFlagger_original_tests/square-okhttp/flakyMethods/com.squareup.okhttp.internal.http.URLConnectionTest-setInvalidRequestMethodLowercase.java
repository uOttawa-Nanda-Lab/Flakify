@Test public void setInvalidRequestMethodLowercase() throws Exception {
  server.play();
  assertInvalidRequestMethod("get");
}
