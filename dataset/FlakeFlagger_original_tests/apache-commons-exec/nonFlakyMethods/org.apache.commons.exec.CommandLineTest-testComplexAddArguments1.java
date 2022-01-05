/** 
 * Create a command line with pre-quoted strings to test SANDBOX-192, e.g. "runMemorySud.cmd", "10", "30", "-XX:+UseParallelGC", "\"-XX:ParallelGCThreads=2\""
 */
public void testComplexAddArguments1(){
  CommandLine cmdl=new CommandLine("runMemorySud.cmd");
  cmdl.addArguments(new String[]{"10","30","-XX:+UseParallelGC","\"-XX:ParallelGCThreads=2\""},false);
  assertEquals("runMemorySud.cmd 10 30 -XX:+UseParallelGC \"-XX:ParallelGCThreads=2\"",cmdl.toString());
  assertEquals(new String[]{"runMemorySud.cmd","10","30","-XX:+UseParallelGC","\"-XX:ParallelGCThreads=2\""},cmdl.toStrings());
}
