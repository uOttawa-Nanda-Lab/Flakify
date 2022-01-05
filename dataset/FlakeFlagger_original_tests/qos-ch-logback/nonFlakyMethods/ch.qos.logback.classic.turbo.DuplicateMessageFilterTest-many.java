@Test public void many(){
  DuplicateMessageFilter dmf=new DuplicateMessageFilter();
  dmf.setAllowedRepetitions(0);
  int cacheSize=10;
  int margin=2;
  dmf.setCacheSize(cacheSize);
  dmf.start();
  for (int i=0; i < cacheSize + margin; i++) {
    assertEquals(FilterReply.NEUTRAL,dmf.decide(null,null,null,"a" + i,null,null));
  }
  for (int i=cacheSize - 1; i >= margin; i--) {
    assertEquals(FilterReply.DENY,dmf.decide(null,null,null,"a" + i,null,null));
  }
  for (int i=margin - 1; i >= 0; i--) {
    assertEquals(FilterReply.NEUTRAL,dmf.decide(null,null,null,"a" + i,null,null));
  }
}
