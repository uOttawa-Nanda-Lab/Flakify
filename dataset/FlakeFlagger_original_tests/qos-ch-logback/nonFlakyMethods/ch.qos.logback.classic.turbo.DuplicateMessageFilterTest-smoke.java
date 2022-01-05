@Test public void smoke(){
  DuplicateMessageFilter dmf=new DuplicateMessageFilter();
  dmf.setAllowedRepetitions(0);
  dmf.start();
  assertEquals(FilterReply.NEUTRAL,dmf.decide(null,null,null,"x",null,null));
  assertEquals(FilterReply.NEUTRAL,dmf.decide(null,null,null,"y",null,null));
  assertEquals(FilterReply.DENY,dmf.decide(null,null,null,"x",null,null));
  assertEquals(FilterReply.DENY,dmf.decide(null,null,null,"y",null,null));
}
