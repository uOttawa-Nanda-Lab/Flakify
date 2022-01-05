@Test public void bufferShouldBeResetBetweenMessages() throws Exception {
  buildSMTPAppender("bufferShouldBeResetBetweenMessages",SYNCHRONOUS);
  smtpAppender.setLayout(buildPatternLayout(DEFAULT_PATTERN));
  smtpAppender.start();
  logger.addAppender(smtpAppender);
  String msg0="hello zero";
  logger.debug(msg0);
  logger.error("error zero");
  String msg1="hello one";
  logger.debug(msg1);
  logger.error("error one");
  Thread.yield();
  int oldCount=0;
  int expectedEmailCount=oldCount + 2;
  waitForServerToReceiveEmails(expectedEmailCount);
  MimeMessage[] mma=greenMailServer.getReceivedMessages();
  assertNotNull(mma);
  assertEquals(expectedEmailCount,mma.length);
  MimeMessage mm0=mma[oldCount];
  MimeMultipart content0=(MimeMultipart)mm0.getContent();
  String body0=GreenMailUtil.getBody(content0.getBodyPart(0));
  MimeMessage mm1=mma[oldCount + 1];
  MimeMultipart content1=(MimeMultipart)mm1.getContent();
  String body1=GreenMailUtil.getBody(content1.getBodyPart(0));
  assertFalse(body1.contains(msg0));
}
