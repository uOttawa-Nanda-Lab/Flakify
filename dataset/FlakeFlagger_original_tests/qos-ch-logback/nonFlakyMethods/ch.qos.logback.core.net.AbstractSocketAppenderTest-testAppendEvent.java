@Test public void testAppendEvent() throws Exception {
  instrumentedAppender.setRemoteHost("localhost");
  instrumentedAppender.setQueueSize(1);
  instrumentedAppender.start();
  executorService.shutdownNow();
  assertTrue(executorService.awaitTermination(DELAY,TimeUnit.MILLISECONDS));
  instrumentedAppender.append("some event");
  assertEquals("some event",instrumentedAppender.lastQueue.poll());
}
