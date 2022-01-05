@Test public void testStopThrowsException() throws Exception {
  receiver.start();
  assertTrue(receiver.isStarted());
  IOException ex=new IOException("test exception");
  runner.setStopException(ex);
  receiver.stop();
  Status status=context.getLastStatus();
  assertNotNull(status);
  assertTrue(status instanceof ErrorStatus);
  assertTrue(status.getMessage().contains(ex.getMessage()));
  assertSame(ex,status.getThrowable());
}
