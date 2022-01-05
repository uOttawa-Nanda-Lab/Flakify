@Test public void testComposite(){
  String compositeMarkerName=COMPOSITE;
  Marker compositeMarker=MarkerFactory.getMarker(compositeMarkerName);
  compositeMarker.add(totoMarker);
  MarkerFilter mkt=new MarkerFilter();
  mkt.setMarker(TOTO);
  mkt.setOnMatch("ACCEPT");
  mkt.setOnMismatch("DENY");
  mkt.start();
  assertTrue(mkt.isStarted());
  assertEquals(FilterReply.DENY,mkt.decide(null,null,null,null,null,null));
  assertEquals(FilterReply.ACCEPT,mkt.decide(totoMarker,null,null,null,null,null));
  assertEquals(FilterReply.ACCEPT,mkt.decide(compositeMarker,null,null,null,null,null));
}
