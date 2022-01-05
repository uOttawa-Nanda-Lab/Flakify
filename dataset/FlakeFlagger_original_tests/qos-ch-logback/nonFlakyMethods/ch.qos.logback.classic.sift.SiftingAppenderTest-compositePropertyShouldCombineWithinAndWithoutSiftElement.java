@Test public void compositePropertyShouldCombineWithinAndWithoutSiftElement() throws JoranException {
  String mdcKey="compositeProperty";
  String mdcVal="" + diff;
  String msg="compositePropertyShouldCombineWithinAndWithoutSiftElement";
  String prefix="composite";
  configure(SIFT_FOLDER_PREFIX + "compositeProperty.xml");
  MDC.put(mdcKey,mdcVal);
  logger.debug(msg);
  SiftingAppender sa=(SiftingAppender)root.getAppender("SIFT");
  StringListAppender<ILoggingEvent> listAppender=(StringListAppender<ILoggingEvent>)sa.getAppenderTracker().find(mdcVal);
  assertNotNull(listAppender);
  List<String> strList=listAppender.strList;
  assertEquals(1,listAppender.strList.size());
  assertEquals(prefix + msg,strList.get(0));
}
