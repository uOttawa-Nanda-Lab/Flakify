@Test public void LBCLASSIC_104() throws Exception {
  String subject="LBCLASSIC_104";
  buildSMTPAppender(subject,SYNCHRONOUS);
  smtpAppender.setAsynchronousSending(false);
  smtpAppender.setLayout(buildPatternLayout(DEFAULT_PATTERN));
  smtpAppender.start();
  logger.addAppender(smtpAppender);
  MDC.put("key","val");
  logger.debug("LBCLASSIC_104");
  MDC.clear();
  logger.error("en error",new Exception("test"));
  MimeMultipart mp=verifyAndExtractMimeMultipart(subject);
  String body=GreenMailUtil.getBody(mp.getBodyPart(0));
  assertTrue(body.startsWith(HEADER.trim()));
  System.out.println(body);
  assertTrue(body.contains("key=val"));
  assertTrue(body.endsWith(FOOTER.trim()));
}
