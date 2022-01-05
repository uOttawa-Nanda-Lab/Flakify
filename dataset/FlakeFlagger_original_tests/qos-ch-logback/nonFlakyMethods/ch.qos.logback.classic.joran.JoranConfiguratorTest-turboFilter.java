@Test public void turboFilter() throws JoranException {
  configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "turbo.xml");
  TurboFilter filter=loggerContext.getTurboFilterList().get(0);
  assertTrue(filter instanceof NOPTurboFilter);
}
