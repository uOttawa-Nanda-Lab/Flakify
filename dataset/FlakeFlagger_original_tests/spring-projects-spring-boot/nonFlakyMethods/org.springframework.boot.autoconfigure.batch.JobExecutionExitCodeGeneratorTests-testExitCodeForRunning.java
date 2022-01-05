@Test public void testExitCodeForRunning(){
  this.generator.onApplicationEvent(new JobExecutionEvent(new JobExecution(0L)));
  assertEquals(1,this.generator.getExitCode());
}
