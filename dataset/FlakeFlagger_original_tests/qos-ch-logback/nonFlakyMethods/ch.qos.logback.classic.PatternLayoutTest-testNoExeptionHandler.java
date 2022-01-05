@Test public void testNoExeptionHandler(){
  pl.setPattern("%m%n");
  pl.start();
  String val=pl.doLayout(le);
  assertTrue(val.contains("java.lang.Exception: Bogus exception"));
}
