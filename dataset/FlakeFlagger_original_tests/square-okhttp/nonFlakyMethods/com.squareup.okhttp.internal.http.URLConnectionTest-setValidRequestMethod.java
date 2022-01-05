@Test public void setValidRequestMethod() throws Exception {
  server.play();
  assertValidRequestMethod("GET");
  assertValidRequestMethod("DELETE");
  assertValidRequestMethod("HEAD");
  assertValidRequestMethod("OPTIONS");
  assertValidRequestMethod("POST");
  assertValidRequestMethod("PUT");
  assertValidRequestMethod("TRACE");
  assertValidRequestMethod("PATCH");
}
