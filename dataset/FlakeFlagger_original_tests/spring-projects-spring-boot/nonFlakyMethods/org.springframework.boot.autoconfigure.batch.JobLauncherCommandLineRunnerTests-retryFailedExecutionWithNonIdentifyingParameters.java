@Test public void retryFailedExecutionWithNonIdentifyingParameters() throws Exception {
  this.job=this.jobs.get("job").start(this.steps.get("step").tasklet(new Tasklet(){
    @Override public RepeatStatus execute(    StepContribution contribution,    ChunkContext chunkContext) throws Exception {
      throw new RuntimeException("Planned");
    }
  }
).build()).incrementer(new RunIdIncrementer()).build();
  JobParameters jobParameters=new JobParametersBuilder().addLong("id",1L,false).addLong("foo",2L,false).toJobParameters();
  this.runner.execute(this.job,jobParameters);
  this.runner.execute(this.job,jobParameters);
  assertEquals(1,this.jobExplorer.getJobInstances("job",0,100).size());
}
