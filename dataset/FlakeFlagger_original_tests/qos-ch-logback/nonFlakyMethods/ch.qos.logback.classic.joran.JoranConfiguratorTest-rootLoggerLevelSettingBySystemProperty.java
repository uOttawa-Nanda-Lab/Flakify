@Test public void rootLoggerLevelSettingBySystemProperty() throws JoranException {
  String propertyName="logback.level";
  System.setProperty(propertyName,"INFO");
  configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "rootLevelByProperty.xml");
  ListAppender listAppender=(ListAppender)root.getAppender("LIST");
  assertEquals(0,listAppender.list.size());
  String msg="hello world";
  logger.debug(msg);
  assertEquals(0,listAppender.list.size());
  System.clearProperty(propertyName);
}
