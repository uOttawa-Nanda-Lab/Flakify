@Test public void localPropertiesShouldBeVisible() throws JoranException {
  String mdcKey="localProperty";
  String mdcVal="" + diff;
  String msg="localPropertiesShouldBeVisible";
  String prefix="Y";
  configure(SIFT_FOLDER_PREFIX + "propertyPropagation.xml");
  MDC.put(mdcKey,mdcVal);
  logger.debug(msg);
  SiftingAppender sa=(SiftingAppender)root.getAppender("SIFT");
  StringListAppender<ILoggingEvent> listAppender=(StringListAppender<ILoggingEvent>)sa.getAppenderTracker().find(mdcVal);
  assertNotNull(listAppender);
  List<String> strList=listAppender.strList;
  assertEquals(1,listAppender.strList.size());
  assertEquals(prefix + msg,strList.get(0));
}
