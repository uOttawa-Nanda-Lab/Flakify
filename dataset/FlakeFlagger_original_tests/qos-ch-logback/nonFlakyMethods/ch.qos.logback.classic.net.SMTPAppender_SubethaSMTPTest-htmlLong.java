@Test public void htmlLong() throws Exception {
  smtpAppender.setLayout(buildHTMLLayout(loggerContext));
  smtpAppender.start();
  Logger logger=loggerContext.getLogger("test");
  logger.addAppender(smtpAppender);
  for (int i=0; i < CoreConstants.TABLE_ROW_LIMIT * 3; i++) {
    logger.debug("hello " + i);
  }
  logger.error("en error",new Exception("an exception"));
  waitUntilEmailIsSent();
  List<WiserMessage> wiserMsgList=WISER.getMessages();
  assertNotNull(wiserMsgList);
  assertEquals(numberOfOldMessages + 1,wiserMsgList.size());
  WiserMessage wm=wiserMsgList.get(numberOfOldMessages);
  MimeMessage mm=wm.getMimeMessage();
  assertEquals(TEST_SUBJECT,mm.getSubject());
  MimeMultipart mp=(MimeMultipart)mm.getContent();
  SAXReader reader=new SAXReader();
  reader.setValidation(true);
  reader.setEntityResolver(new XHTMLEntityResolver());
  reader.read(mp.getBodyPart(0).getInputStream());
}
