@Test public void retryFailedExecution() throws Exception {
  this.job=this.jobs.get("job").start(this.steps.get("step").tasklet(new Tasklet(){
    @Override public RepeatStatus execute(    StepContribution contribution,    ChunkContext chunkContext) throws Exception {
      throw new RuntimeException("Planned");
    }
  }
).build()).incrementer(new RunIdIncrementer()).build();
  this.runner.execute(this.job,new JobParameters());
  this.runner.execute(this.job,new JobParameters());
  assertEquals(1,this.jobExplorer.getJobInstances("job",0,100).size());
}
