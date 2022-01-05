@Test public void additivity() throws JoranException {
  configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "additivity.xml");
  Logger logger=loggerContext.getLogger("additivityTest");
  assertFalse(logger.isAdditive());
}
