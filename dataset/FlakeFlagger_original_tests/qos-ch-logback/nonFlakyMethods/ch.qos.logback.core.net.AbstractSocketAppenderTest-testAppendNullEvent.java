@Test public void testAppendNullEvent() throws Exception {
  instrumentedAppender.setRemoteHost("localhost");
  instrumentedAppender.start();
  instrumentedAppender.append("some event");
  assertTrue(instrumentedAppender.lastQueue.isEmpty());
}
