@Test public void testOK(){
  pl.setPattern("%d %le [%t] %lo{30} - %m%n");
  pl.start();
  String val=pl.doLayout(getEventObject());
  String regex=ISO_REGEX + " INFO " + MAIN_REGEX+ " c.q.l.c.pattern.ConverterTest - Some message\\s*";
  assertTrue("val=" + val,val.matches(regex));
}
