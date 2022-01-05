@Test public void testMultipleTo() throws Exception {
  buildSMTPAppender("testMultipleTo",SYNCHRONOUS);
  smtpAppender.setLayout(buildPatternLayout(DEFAULT_PATTERN));
  smtpAppender.addTo("Test <test@example.com>, other-test@example.com");
  smtpAppender.start();
  logger.addAppender(smtpAppender);
  logger.debug("testMultipleTo hello");
  logger.error("testMultipleTo en error",new Exception("an exception"));
  Thread.yield();
  int expectedEmailCount=3;
  waitForServerToReceiveEmails(expectedEmailCount);
  MimeMessage[] mma=greenMailServer.getReceivedMessages();
  assertNotNull(mma);
  assertEquals(expectedEmailCount,mma.length);
}
