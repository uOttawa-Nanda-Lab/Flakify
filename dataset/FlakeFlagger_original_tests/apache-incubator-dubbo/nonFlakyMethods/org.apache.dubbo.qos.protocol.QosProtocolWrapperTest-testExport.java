@Test public void testExport() throws Exception {
  wrapper.export(invoker);
  assertThat(server.isStarted(),is(true));
  assertThat(server.getPort(),is(12345));
  assertThat(server.isAcceptForeignIp(),is(false));
  verify(protocol).export(invoker);
}
