@Test public void testAppendWhenNotStarted() throws Exception {
  instrumentedAppender.setRemoteHost("localhost");
  instrumentedAppender.start();
  instrumentedAppender.stop();
  executorService.shutdownNow();
  assertTrue(executorService.awaitTermination(DELAY,TimeUnit.MILLISECONDS));
  instrumentedAppender.append("some event");
  assertTrue(instrumentedAppender.lastQueue.isEmpty());
}
