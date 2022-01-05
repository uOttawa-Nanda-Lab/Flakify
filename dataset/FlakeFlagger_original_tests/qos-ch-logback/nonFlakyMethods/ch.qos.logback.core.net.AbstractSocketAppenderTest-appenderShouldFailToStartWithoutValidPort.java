@Test public void appenderShouldFailToStartWithoutValidPort() throws Exception {
  instrumentedAppender.setPort(-1);
  instrumentedAppender.setRemoteHost("localhost");
  instrumentedAppender.setQueueSize(0);
  instrumentedAppender.start();
  assertFalse(instrumentedAppender.isStarted());
  assertTrue(mockContext.getLastStatus().getMessage().contains("port"));
}
