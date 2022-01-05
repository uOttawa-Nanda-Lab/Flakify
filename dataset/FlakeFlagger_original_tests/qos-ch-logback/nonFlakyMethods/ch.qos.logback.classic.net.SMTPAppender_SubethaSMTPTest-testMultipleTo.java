@Test public void testMultipleTo() throws Exception {
  smtpAppender.setLayout(buildPatternLayout(loggerContext));
  smtpAppender.addTo("Test <test@example.com>, other-test@example.com");
  smtpAppender.start();
  Logger logger=loggerContext.getLogger("test");
  logger.addAppender(smtpAppender);
  logger.debug("hello");
  logger.error("en error",new Exception("an exception"));
  waitUntilEmailIsSent();
  List<WiserMessage> wiserMsgList=WISER.getMessages();
  assertNotNull(wiserMsgList);
  assertEquals(numberOfOldMessages + 3,wiserMsgList.size());
}
