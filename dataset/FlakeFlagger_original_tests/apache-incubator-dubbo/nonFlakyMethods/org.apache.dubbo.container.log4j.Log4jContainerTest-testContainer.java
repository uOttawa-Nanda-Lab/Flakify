@Test public void testContainer(){
  Log4jContainer container=(Log4jContainer)ExtensionLoader.getExtensionLoader(Container.class).getExtension("log4j");
  container.start();
  container.stop();
}
