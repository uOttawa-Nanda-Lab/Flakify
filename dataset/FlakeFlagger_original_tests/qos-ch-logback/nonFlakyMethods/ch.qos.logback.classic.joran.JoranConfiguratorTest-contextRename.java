@Test public void contextRename() throws JoranException {
  loggerContext.setName(CoreConstants.DEFAULT_CONTEXT_NAME);
  configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "contextRename.xml");
  assertEquals("wombat",loggerContext.getName());
}
