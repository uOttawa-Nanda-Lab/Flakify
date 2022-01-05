@Test public void testCustomEvaluator() throws Exception {
  configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "smtp/customEvaluator.xml");
  logger.debug("test");
  String msg2="CustomEvaluator";
  logger.debug(msg2);
  logger.debug("invisible");
  waitUntilEmailIsSent();
  MimeMultipart mp=verifyAndExtractMimeMultipart("testCustomEvaluator " + this.getClass().getName() + " - "+ msg2);
  String body=GreenMailUtil.getBody(mp.getBodyPart(0));
  assertEquals("testCustomEvaluator",body);
}
