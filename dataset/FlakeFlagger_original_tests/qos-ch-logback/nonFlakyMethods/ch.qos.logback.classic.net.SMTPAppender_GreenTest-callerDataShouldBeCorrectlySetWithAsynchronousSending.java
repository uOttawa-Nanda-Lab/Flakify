@Test public void callerDataShouldBeCorrectlySetWithAsynchronousSending() throws Exception {
  String subject="LOGBACK-734";
  buildSMTPAppender("LOGBACK-734",ASYNCHRONOUS);
  smtpAppender.setLayout(buildPatternLayout(DEFAULT_PATTERN));
  smtpAppender.setIncludeCallerData(true);
  smtpAppender.start();
  logger.addAppender(smtpAppender);
  logger.debug("LOGBACK-734");
  logger.error("callerData",new Exception("ShouldBeCorrectlySetWithAsynchronousSending"));
  waitUntilEmailIsSent();
  MimeMultipart mp=verifyAndExtractMimeMultipart(subject);
  String body=GreenMailUtil.getBody(mp.getBodyPart(0));
  assertTrue("actual [" + body + "]",body.contains("DEBUG " + this.getClass().getName() + " - LOGBACK-734"));
}
