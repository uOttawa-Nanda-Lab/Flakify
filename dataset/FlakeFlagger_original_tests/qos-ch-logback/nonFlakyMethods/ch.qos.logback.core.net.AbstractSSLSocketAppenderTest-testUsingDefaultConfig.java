@Test public void testUsingDefaultConfig() throws Exception {
  appender.start();
  assertNotNull(appender.getSocketFactory());
  appender.stop();
}
