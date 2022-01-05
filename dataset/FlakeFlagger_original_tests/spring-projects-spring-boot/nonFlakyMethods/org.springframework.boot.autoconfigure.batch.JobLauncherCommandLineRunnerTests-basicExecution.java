@Test public void basicExecution() throws Exception {
  this.runner.execute(this.job,new JobParameters());
  assertEquals(1,this.jobExplorer.getJobInstances("job",0,100).size());
  this.runner.execute(this.job,new JobParametersBuilder().addLong("id",1L).toJobParameters());
  assertEquals(2,this.jobExplorer.getJobInstances("job",0,100).size());
}
