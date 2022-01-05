@Test public void cnTest(){
  pl.setPattern("%cn");
  lc.setName("aValue");
  pl.start();
  String val=pl.doLayout(getEventObject());
  assertEquals("aValue",val);
}
