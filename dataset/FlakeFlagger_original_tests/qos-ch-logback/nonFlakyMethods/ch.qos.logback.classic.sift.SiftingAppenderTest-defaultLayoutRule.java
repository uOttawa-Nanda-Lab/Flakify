@Test public void defaultLayoutRule() throws JoranException {
  configure(SIFT_FOLDER_PREFIX + "defaultLayoutRule.xml");
  logger.debug("hello");
  SiftingAppender ha=(SiftingAppender)root.getAppender("SIFT");
  StringListAppender<ILoggingEvent> listAppender=(StringListAppender<ILoggingEvent>)ha.getAppenderTracker().find("default");
  assertNotNull(listAppender);
  List<String> strList=listAppender.strList;
  assertEquals(1,strList.size());
  assertEquals("DEBUG hello",strList.get(0));
}
