@Test public void eval() throws JoranException {
  configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "callerData.xml");
  String msg="hello world";
  logger.debug("toto");
  logger.debug(msg);
  StringListAppender<ILoggingEvent> slAppender=(StringListAppender<ILoggingEvent>)loggerContext.getLogger("root").getAppender("STR_LIST");
  assertNotNull(slAppender);
  assertEquals(2,slAppender.strList.size());
  assertTrue(slAppender.strList.get(0).contains(" DEBUG - toto"));
  String str1=slAppender.strList.get(1);
  assertTrue(str1.contains("Caller+0"));
  assertTrue(str1.contains(" DEBUG - hello world"));
}
