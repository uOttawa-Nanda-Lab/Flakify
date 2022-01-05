@Test public void synchronousSmoke() throws Exception {
  String subject="synchronousSmoke";
  buildSMTPAppender(subject,SYNCHRONOUS);
  smtpAppender.setLayout(buildPatternLayout(DEFAULT_PATTERN));
  smtpAppender.start();
  logger.addAppender(smtpAppender);
  logger.debug("hello");
  logger.error("en error",new Exception("an exception"));
  MimeMultipart mp=verifyAndExtractMimeMultipart(subject);
  String body=GreenMailUtil.getBody(mp.getBodyPart(0));
  assertTrue(body.startsWith(HEADER.trim()));
  assertTrue(body.endsWith(FOOTER.trim()));
}
