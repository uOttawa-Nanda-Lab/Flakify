@Test public void testTurboFilterWithStringList() throws JoranException {
  configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "turbo2.xml");
  TurboFilter filter=loggerContext.getTurboFilterList().get(0);
  assertTrue(filter instanceof DebugUsersTurboFilter);
  DebugUsersTurboFilter dutf=(DebugUsersTurboFilter)filter;
  assertEquals(2,dutf.getUsers().size());
}
