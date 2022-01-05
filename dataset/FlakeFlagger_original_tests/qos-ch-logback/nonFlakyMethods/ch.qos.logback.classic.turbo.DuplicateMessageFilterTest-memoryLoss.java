@Test public void memoryLoss(){
  DuplicateMessageFilter dmf=new DuplicateMessageFilter();
  dmf.setAllowedRepetitions(1);
  dmf.setCacheSize(1);
  dmf.start();
  assertEquals(FilterReply.NEUTRAL,dmf.decide(null,null,null,"a",null,null));
  assertEquals(FilterReply.NEUTRAL,dmf.decide(null,null,null,"b",null,null));
  assertEquals(FilterReply.NEUTRAL,dmf.decide(null,null,null,"a",null,null));
}
