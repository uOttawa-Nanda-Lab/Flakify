@Test public void testExitCodeForCompleted(){
  JobExecution execution=new JobExecution(0L);
  execution.setStatus(BatchStatus.COMPLETED);
  this.generator.onApplicationEvent(new JobExecutionEvent(execution));
  assertEquals(0,this.generator.getExitCode());
}
