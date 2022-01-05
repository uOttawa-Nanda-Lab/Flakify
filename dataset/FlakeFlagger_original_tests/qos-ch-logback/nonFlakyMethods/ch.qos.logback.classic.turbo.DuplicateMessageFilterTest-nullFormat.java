@Test public void nullFormat(){
  DuplicateMessageFilter dmf=new DuplicateMessageFilter();
  dmf.setAllowedRepetitions(0);
  dmf.setCacheSize(10);
  dmf.start();
  assertEquals(FilterReply.NEUTRAL,dmf.decide(null,null,null,null,null,null));
  assertEquals(FilterReply.NEUTRAL,dmf.decide(null,null,null,null,null,null));
}
