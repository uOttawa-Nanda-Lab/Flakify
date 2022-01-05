@Test public void testRefer() throws Exception {
  wrapper.refer(BaseCommand.class,url);
  assertThat(server.isStarted(),is(true));
  assertThat(server.getPort(),is(12345));
  assertThat(server.isAcceptForeignIp(),is(false));
  verify(protocol).refer(BaseCommand.class,url);
}
