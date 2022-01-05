@Test public void appenderShouldFailToStartWithUnresolvableRemoteHost() throws Exception {
  instrumentedAppender.setPort(1);
  instrumentedAppender.setRemoteHost("NOT.A.VALID.REMOTE.HOST.NAME");
  instrumentedAppender.setQueueSize(0);
  instrumentedAppender.start();
  assertFalse(instrumentedAppender.isStarted());
  assertTrue(mockContext.getLastStatus().getMessage().contains("unknown host"));
}
