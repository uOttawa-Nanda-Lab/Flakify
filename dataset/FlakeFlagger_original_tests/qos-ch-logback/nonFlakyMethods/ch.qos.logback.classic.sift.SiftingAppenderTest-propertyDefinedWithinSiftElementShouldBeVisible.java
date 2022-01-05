@Test public void propertyDefinedWithinSiftElementShouldBeVisible() throws JoranException {
  String mdcKey="propertyDefinedWithinSift";
  String mdcVal="" + diff;
  String msg="propertyDefinedWithinSiftElementShouldBeVisible";
  String prefix="Y";
  configure(SIFT_FOLDER_PREFIX + "propertyDefinedInSiftElement.xml");
  MDC.put(mdcKey,mdcVal);
  logger.debug(msg);
  SiftingAppender sa=(SiftingAppender)root.getAppender("SIFT");
  StringListAppender<ILoggingEvent> listAppender=(StringListAppender<ILoggingEvent>)sa.getAppenderTracker().find(mdcVal);
  assertNotNull(listAppender);
  List<String> strList=listAppender.strList;
  assertEquals(1,listAppender.strList.size());
  assertEquals(prefix + msg,strList.get(0));
}
