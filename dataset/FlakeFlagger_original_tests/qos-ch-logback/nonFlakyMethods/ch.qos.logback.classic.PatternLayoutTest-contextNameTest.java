@Test public void contextNameTest(){
  pl.setPattern("%contextName");
  lc.setName("aValue");
  pl.start();
  String val=pl.doLayout(getEventObject());
  assertEquals("aValue",val);
}
