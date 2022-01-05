/** 
 * Create a command line with pre-quoted strings to test SANDBOX-192, e.g. "runMemorySud.cmd", "10", "30", "-XX:+UseParallelGC", "\"-XX:ParallelGCThreads=2\""
 */
public void testComplexAddArgument(){
  CommandLine cmdl=new CommandLine("runMemorySud.cmd");
  cmdl.addArgument("10",false);
  cmdl.addArgument("30",false);
  cmdl.addArgument("-XX:+UseParallelGC",false);
  cmdl.addArgument("\"-XX:ParallelGCThreads=2\"",false);
  assertEquals("runMemorySud.cmd 10 30 -XX:+UseParallelGC \"-XX:ParallelGCThreads=2\"",cmdl.toString());
  assertEquals(new String[]{"runMemorySud.cmd","10","30","-XX:+UseParallelGC","\"-XX:ParallelGCThreads=2\""},cmdl.toStrings());
}
