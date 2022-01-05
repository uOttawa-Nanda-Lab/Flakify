@Test public void testExitCodeForFailed(){
  JobExecution execution=new JobExecution(0L);
  execution.setStatus(BatchStatus.FAILED);
  this.generator.onApplicationEvent(new JobExecutionEvent(execution));
  assertEquals(5,this.generator.getExitCode());
}
