@Test public void appenderShouldFailToStartWithNegativeQueueSize() throws Exception {
  instrumentedAppender.setPort(1);
  instrumentedAppender.setRemoteHost("localhost");
  instrumentedAppender.setQueueSize(-1);
  instrumentedAppender.start();
  assertFalse(instrumentedAppender.isStarted());
  assertTrue(mockContext.getLastStatus().getMessage().contains("Queue"));
}
