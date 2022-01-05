@Test public void quoteHandledProperly(){
  String[] args=parseArgs("-Dvalue=\"My Value\"    ");
  assertEquals(1,args.length);
  assertEquals("-Dvalue=My Value",args[0]);
}
