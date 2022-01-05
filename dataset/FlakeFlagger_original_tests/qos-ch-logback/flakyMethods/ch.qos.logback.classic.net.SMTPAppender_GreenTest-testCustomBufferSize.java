@Test public void testCustomBufferSize() throws Exception {
  configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "smtp/customBufferSize.xml");
  logger.debug("invisible1");
  logger.debug("invisible2");
  String msg="hello";
  logger.error(msg);
  waitUntilEmailIsSent();
  MimeMultipart mp=verifyAndExtractMimeMultipart("testCustomBufferSize " + this.getClass().getName() + " - "+ msg);
  String body=GreenMailUtil.getBody(mp.getBodyPart(0));
  assertEquals(msg,body);
}
