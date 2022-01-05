@Test public void testUsingDefaultConfig() throws Exception {
  appender.start();
  assertNotNull(appender.getServerSocketFactory());
  appender.stop();
}
