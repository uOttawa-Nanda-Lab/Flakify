@Test public void testContainer(){
  LogbackContainer container=(LogbackContainer)ExtensionLoader.getExtensionLoader(Container.class).getExtension("logback");
  container.start();
  logger.debug("Test debug:" + this.getClass().getName());
  logger.warn("Test warn:" + this.getClass().getName());
  logger.info("Test info:" + this.getClass().getName());
  logger.error("Test error:" + this.getClass().getName());
  container.stop();
}
