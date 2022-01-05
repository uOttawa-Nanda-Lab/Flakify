public void testJobCommandsWithTimer(){
  processEngineConfiguration.getClock().setCurrentTime(new Date(SOME_TIME));
  AsyncExecutor asyncExecutor=processEngineConfiguration.getAsyncExecutor();
  CommandExecutor commandExecutor=processEngineConfiguration.getCommandExecutor();
  String jobId=commandExecutor.execute(new Command<String>(){
    public String execute(    CommandContext commandContext){
      TimerJobEntity timer=createTweetTimer("i'm coding a test",new Date(SOME_TIME + (10 * SECOND)));
      commandContext.getJobManager().scheduleTimerJob(timer);
      return timer.getId();
    }
  }
);
  AcquiredTimerJobEntities acquiredJobs=commandExecutor.execute(new AcquireTimerJobsCmd(asyncExecutor));
  assertEquals(0,acquiredJobs.size());
  processEngineConfiguration.getClock().setCurrentTime(new Date(SOME_TIME + (20 * SECOND)));
  acquiredJobs=commandExecutor.execute(new AcquireTimerJobsCmd(asyncExecutor));
  assertEquals(1,acquiredJobs.size());
  TimerJobEntity job=acquiredJobs.getJobs().iterator().next();
  assertEquals(jobId,job.getId());
  assertEquals(0,tweetHandler.getMessages().size());
  Job executableJob=managementService.moveTimerToExecutableJob(jobId);
  commandExecutor.execute(new ExecuteAsyncJobCmd(executableJob.getId()));
  assertEquals("i'm coding a test",tweetHandler.getMessages().get(0));
  assertEquals(1,tweetHandler.getMessages().size());
}
