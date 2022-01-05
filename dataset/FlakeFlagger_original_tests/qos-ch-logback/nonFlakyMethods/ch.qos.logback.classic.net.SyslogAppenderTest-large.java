@Test public void large() throws InterruptedException {
  setMockServerAndConfigure(2);
  StringBuilder largeBuf=new StringBuilder();
  for (int i=0; i < 2 * 1024 * 1024; i++) {
    largeBuf.append('a');
  }
  logger.debug(largeBuf.toString());
  String logMsg="hello";
  logger.debug(logMsg);
  Thread.sleep(RecoveryCoordinator.BACKOFF_COEFFICIENT_MIN + 10);
  logger.debug(logMsg);
  mockServer.join(8000);
  assertTrue(mockServer.isFinished());
  assertEquals(2,mockServer.getMessageList().size());
  String expected="<" + (SyslogConstants.LOG_MAIL + SyslogConstants.DEBUG_SEVERITY) + ">";
  String expectedPrefix="<\\d{2}>\\w{3} \\d{2} \\d{2}(:\\d{2}){2} [\\w.-]* ";
  String threadName=Thread.currentThread().getName();
  String largeMsg=mockServer.getMessageList().get(0);
  assertTrue(largeMsg.startsWith(expected));
  String largeRegex=expectedPrefix + "\\[" + threadName+ "\\] "+ loggerName+ " "+ "a{64000,66000}";
  checkRegexMatch(largeMsg,largeRegex);
  String msg=mockServer.getMessageList().get(1);
  assertTrue(msg.startsWith(expected));
  String regex=expectedPrefix + "\\[" + threadName+ "\\] "+ loggerName+ " "+ logMsg;
  checkRegexMatch(msg,regex);
}
