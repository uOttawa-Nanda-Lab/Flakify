@Test public void parseNull(){
  String[] args=parseArgs(null);
  assertNotNull(args);
  assertEquals(0,args.length);
}
