@Test public void parseEmpty(){
  String[] args=parseArgs("   ");
  assertNotNull(args);
  assertEquals(0,args.length);
}
