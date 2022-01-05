@Test public void incrementExistingExecution() throws Exception {
  this.job=this.jobs.get("job").start(this.step).incrementer(new RunIdIncrementer()).build();
  this.runner.execute(this.job,new JobParameters());
  this.runner.execute(this.job,new JobParameters());
  assertEquals(2,this.jobExplorer.getJobInstances("job",0,100).size());
}
