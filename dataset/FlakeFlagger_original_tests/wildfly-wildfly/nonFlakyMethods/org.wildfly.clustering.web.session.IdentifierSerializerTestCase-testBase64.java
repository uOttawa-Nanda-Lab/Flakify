@Test public void testBase64() throws IOException {
  io.undertow.server.session.SessionIdGenerator generator=new SecureRandomSessionIdGenerator();
  test(IdentifierSerializer.BASE64,() -> generator.createSessionId());
}
