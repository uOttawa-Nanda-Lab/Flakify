@Test public void loggerLevelSettingBySystemProperty() throws JoranException {
  String propertyName="logback.level";
  System.setProperty(propertyName,"DEBUG");
  configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "loggerLevelByProperty.xml");
  ListAppender listAppender=(ListAppender)root.getAppender("LIST");
  assertEquals(0,listAppender.list.size());
  String msg="hello world";
  logger.debug(msg);
  assertEquals(1,listAppender.list.size());
  System.clearProperty(propertyName);
}
