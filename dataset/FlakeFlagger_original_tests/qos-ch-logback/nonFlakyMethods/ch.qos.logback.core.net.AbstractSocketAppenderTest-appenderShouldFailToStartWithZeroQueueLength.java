@Test public void appenderShouldFailToStartWithZeroQueueLength() throws Exception {
  instrumentedAppender.setPort(1);
  instrumentedAppender.setRemoteHost("localhost");
  instrumentedAppender.setQueueSize(0);
  instrumentedAppender.start();
  assertTrue(instrumentedAppender.isStarted());
  assertTrue(instrumentedAppender.lastQueue instanceof SynchronousQueue);
}
