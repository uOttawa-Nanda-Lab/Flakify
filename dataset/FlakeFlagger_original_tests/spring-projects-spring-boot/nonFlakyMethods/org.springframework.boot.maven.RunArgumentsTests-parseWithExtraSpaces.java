@Test public void parseWithExtraSpaces(){
  String[] args=parseArgs("     -Dfoo=bar        -Dfoo2=bar2  ");
  assertEquals(2,args.length);
  assertEquals("-Dfoo=bar",args[0]);
  assertEquals("-Dfoo2=bar2",args[1]);
}
