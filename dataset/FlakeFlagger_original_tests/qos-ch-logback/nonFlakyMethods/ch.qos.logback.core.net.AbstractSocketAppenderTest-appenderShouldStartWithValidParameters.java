@Test public void appenderShouldStartWithValidParameters() throws Exception {
  instrumentedAppender.setPort(1);
  instrumentedAppender.setRemoteHost("localhost");
  instrumentedAppender.setQueueSize(1);
  instrumentedAppender.start();
  assertTrue(instrumentedAppender.isStarted());
  assertTrue(instrumentedAppender.lastQueue instanceof ArrayBlockingQueue);
  assertEquals(1,instrumentedAppender.lastQueue.remainingCapacity());
}
