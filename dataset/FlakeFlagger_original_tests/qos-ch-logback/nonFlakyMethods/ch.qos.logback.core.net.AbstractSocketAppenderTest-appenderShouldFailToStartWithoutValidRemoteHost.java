@Test public void appenderShouldFailToStartWithoutValidRemoteHost() throws Exception {
  instrumentedAppender.setPort(1);
  instrumentedAppender.setRemoteHost(null);
  instrumentedAppender.setQueueSize(0);
  instrumentedAppender.start();
  assertFalse(instrumentedAppender.isStarted());
  assertTrue(mockContext.getLastStatus().getMessage().contains("remote host"));
}
