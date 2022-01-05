@Test public void testNopExeptionHandler(){
  pl.setPattern("%nopex %m%n");
  pl.start();
  String val=pl.doLayout(le);
  assertTrue(!val.contains("java.lang.Exception: Bogus exception"));
}
