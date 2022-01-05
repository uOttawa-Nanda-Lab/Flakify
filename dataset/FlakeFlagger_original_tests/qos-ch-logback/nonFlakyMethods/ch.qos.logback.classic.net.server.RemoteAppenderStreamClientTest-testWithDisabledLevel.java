@Test public void testWithDisabledLevel() throws Exception {
  logger.setLevel(Level.INFO);
  client.run();
  client.close();
  assertNull(appender.getLastEvent());
}
