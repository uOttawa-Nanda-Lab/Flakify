@Test public void testNoMarker(){
  MarkerFilter mkt=new MarkerFilter();
  mkt.start();
  assertFalse(mkt.isStarted());
  assertEquals(FilterReply.NEUTRAL,mkt.decide(totoMarker,null,null,null,null,null));
  assertEquals(FilterReply.NEUTRAL,mkt.decide(null,null,null,null,null,null));
}
