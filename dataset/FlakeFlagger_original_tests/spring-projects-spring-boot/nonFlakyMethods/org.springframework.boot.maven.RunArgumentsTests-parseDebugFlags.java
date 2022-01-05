@Test public void parseDebugFlags(){
  String[] args=parseArgs("-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005");
  assertEquals(2,args.length);
  assertEquals("-Xdebug",args[0]);
  assertEquals("-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005",args[1]);
}
