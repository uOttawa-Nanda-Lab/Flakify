@Test public void should_exception_when_proxy() throws Exception {
  exception.expect(IllegalStateException.class);
  exception.expectMessage("Then entity is already in 'managed' state");
  proxifier.ensureNotProxy(realProxy);
}
