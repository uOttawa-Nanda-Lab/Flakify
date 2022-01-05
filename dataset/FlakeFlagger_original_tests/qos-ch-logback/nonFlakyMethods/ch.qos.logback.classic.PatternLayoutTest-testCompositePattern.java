@Test public void testCompositePattern(){
  pl.setPattern("%-56(%d %lo{20}) - %m%n");
  pl.start();
  String val=pl.doLayout(getEventObject());
  String regex=ISO_REGEX + " c.q.l.c.p.ConverterTest          - Some message\\s*";
  assertTrue(val.matches(regex));
}
