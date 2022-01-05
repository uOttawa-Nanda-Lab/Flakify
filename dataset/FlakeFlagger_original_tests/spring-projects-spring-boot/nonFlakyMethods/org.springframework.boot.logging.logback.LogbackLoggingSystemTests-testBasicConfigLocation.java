@Test public void testBasicConfigLocation() throws Exception {
  this.loggingSystem.beforeInitialize();
  ILoggerFactory factory=StaticLoggerBinder.getSingleton().getLoggerFactory();
  LoggerContext context=(LoggerContext)factory;
  Logger root=context.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
  assertNotNull(root.getAppender("CONSOLE"));
}
