@Test public void testWithLettersComingFromLog4j(){
  pl.setPattern("%d %p [%t] %c{30} - %m%n");
  pl.start();
  String val=pl.doLayout(getEventObject());
  String regex=ClassicTestConstants.ISO_REGEX + " INFO " + MAIN_REGEX+ " c.q.l.c.pattern.ConverterTest - Some message\\s*";
  assertTrue(val.matches(regex));
}
