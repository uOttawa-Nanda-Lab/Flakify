@Test public void tException() throws InterruptedException {
  setMockServerAndConfigure(21);
  String logMsg="hello";
  String exMsg="just testing";
  Exception ex=new Exception(exMsg);
  logger.debug(logMsg,ex);
  StatusPrinter.print(lc);
  mockServer.join(8000);
  assertTrue(mockServer.isFinished());
  assertEquals(21,mockServer.getMessageList().size());
  String msg=mockServer.getMessageList().get(0);
  String expected="<" + (SyslogConstants.LOG_MAIL + SyslogConstants.DEBUG_SEVERITY) + ">";
  assertTrue(msg.startsWith(expected));
  String expectedPrefix="<\\d{2}>\\w{3} \\d{2} \\d{2}(:\\d{2}){2} [\\w.-]* ";
  String threadName=Thread.currentThread().getName();
  String regex=expectedPrefix + "\\[" + threadName+ "\\] "+ loggerName+ " "+ logMsg;
  checkRegexMatch(msg,regex);
  msg=mockServer.getMessageList().get(1);
  assertTrue(msg.contains(ex.getClass().getName()));
  assertTrue(msg.contains(ex.getMessage()));
  msg=mockServer.getMessageList().get(2);
  assertTrue(msg.startsWith(expected));
  regex=expectedPrefix + "\\[" + threadName+ "\\] "+ "foo "+ CoreConstants.TAB+ "at ch\\.qos.*";
  checkRegexMatch(msg,regex);
}
