@Test public void basic() throws InterruptedException {
  setMockServerAndConfigure(1);
  String logMsg="hello";
  logger.debug(logMsg);
  mockServer.join(8000);
  assertTrue(mockServer.isFinished());
  assertEquals(1,mockServer.getMessageList().size());
  String msg=mockServer.getMessageList().get(0);
  String threadName=Thread.currentThread().getName();
  String expected="<" + (SyslogConstants.LOG_MAIL + SyslogConstants.DEBUG_SEVERITY) + ">";
  assertTrue(msg.startsWith(expected));
  String first="<\\d{2}>\\w{3} \\d{2} \\d{2}(:\\d{2}){2} [\\w.-]* ";
  checkRegexMatch(msg,first + "\\[" + threadName+ "\\] "+ loggerName+ " "+ logMsg);
}
