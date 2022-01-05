@Test public void appenderRefSettingBySystemProperty() throws JoranException {
  final String propertyName="logback.appenderRef";
  System.setProperty(propertyName,"A");
  configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "appenderRefByProperty.xml");
  final Logger logger=loggerContext.getLogger("ch.qos.logback.classic.joran");
  final ListAppender listAppender=(ListAppender)logger.getAppender("A");
  assertEquals(0,listAppender.list.size());
  final String msg="hello world";
  logger.info(msg);
  assertEquals(1,listAppender.list.size());
  System.clearProperty(propertyName);
}
