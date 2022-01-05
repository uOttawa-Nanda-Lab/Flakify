@Test public void properties() throws JoranException {
  String configFileAsStr=ClassicTestConstants.JORAN_INPUT_PREFIX + "properties.xml";
  assertNull(loggerContext.getProperty(CoreConstants.HOSTNAME_KEY));
  assertNull(System.getProperty("sys"));
  configure(configFileAsStr);
  assertNotNull(loggerContext.getProperty(CoreConstants.HOSTNAME_KEY));
  assertNull(loggerContext.getProperty("transientKey1"));
  assertNull(loggerContext.getProperty("transientKey2"));
  assertEquals("node0",loggerContext.getProperty("nodeId"));
  assertEquals("tem",System.getProperty("sys"));
  assertNotNull(loggerContext.getProperty("path"));
  checker.assertIsErrorFree();
}
