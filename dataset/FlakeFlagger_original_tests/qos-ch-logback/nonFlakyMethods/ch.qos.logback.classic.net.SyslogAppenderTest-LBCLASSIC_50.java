@Test public void LBCLASSIC_50() throws JoranException {
  LoggerContext lc=(LoggerContext)LoggerFactory.getILoggerFactory();
  JoranConfigurator configurator=new JoranConfigurator();
  configurator.setContext(lc);
  lc.reset();
  configurator.doConfigure(ClassicTestConstants.JORAN_INPUT_PREFIX + "syslog_LBCLASSIC_50.xml");
  org.slf4j.Logger logger=LoggerFactory.getLogger(this.getClass());
  logger.info("hello");
}
