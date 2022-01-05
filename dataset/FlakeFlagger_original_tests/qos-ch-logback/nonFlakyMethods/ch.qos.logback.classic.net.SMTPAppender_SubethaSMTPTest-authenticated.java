@Test public void authenticated() throws Exception {
  MessageListenerAdapter mla=(MessageListenerAdapter)WISER.getServer().getMessageHandlerFactory();
  mla.setAuthenticationHandlerFactory(new TrivialAuthHandlerFactory());
  smtpAppender.setUsername("x");
  smtpAppender.setPassword("x");
  smtpAppender.setLayout(buildPatternLayout(loggerContext));
  smtpAppender.start();
  Logger logger=loggerContext.getLogger("test");
  logger.addAppender(smtpAppender);
  logger.debug("hello");
  logger.error("en error",new Exception("an exception"));
  waitUntilEmailIsSent();
  List<WiserMessage> wiserMsgList=WISER.getMessages();
  assertNotNull(wiserMsgList);
  assertEquals(numberOfOldMessages + 1,wiserMsgList.size());
  WiserMessage wm=wiserMsgList.get(numberOfOldMessages);
  MimeMessage mm=wm.getMimeMessage();
  assertEquals(TEST_SUBJECT,mm.getSubject());
  MimeMultipart mp=(MimeMultipart)mm.getContent();
  String body=getBody(mp.getBodyPart(0));
  assertTrue(body.startsWith(HEADER.trim()));
  assertTrue(body.endsWith(FOOTER.trim()));
}
