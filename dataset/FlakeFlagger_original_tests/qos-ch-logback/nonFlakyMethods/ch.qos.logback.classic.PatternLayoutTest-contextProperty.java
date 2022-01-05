@Test public void contextProperty(){
  pl.setPattern("%property{a}");
  pl.start();
  lc.putProperty("a","b");
  String val=pl.doLayout(getEventObject());
  assertEquals("b",val);
}
