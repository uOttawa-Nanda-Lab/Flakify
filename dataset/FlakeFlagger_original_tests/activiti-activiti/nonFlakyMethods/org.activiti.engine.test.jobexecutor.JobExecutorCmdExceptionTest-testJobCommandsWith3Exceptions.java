public void testJobCommandsWith3Exceptions(){
  tweetExceptionHandler.setExceptionsRemaining(3);
  commandExecutor.execute(new Command<String>(){
    public String execute(    CommandContext commandContext){
      JobEntity message=createTweetExceptionMessage();
      commandContext.getJobManager().scheduleAsyncJob(message);
      return message.getId();
    }
  }
);
  Job job=managementService.createJobQuery().singleResult();
  assertEquals(3,job.getRetries());
  try {
    managementService.executeJob(job.getId());
    fail("exception expected");
  }
 catch (  Exception e) {
  }
  job=managementService.createTimerJobQuery().singleResult();
  assertEquals(2,job.getRetries());
  try {
    managementService.moveTimerToExecutableJob(job.getId());
    managementService.executeJob(job.getId());
    fail("exception expected");
  }
 catch (  Exception e) {
  }
  job=managementService.createTimerJobQuery().singleResult();
  assertEquals(1,job.getRetries());
  try {
    managementService.moveTimerToExecutableJob(job.getId());
    managementService.executeJob(job.getId());
    fail("exception expected");
  }
 catch (  Exception e) {
  }
  job=managementService.createDeadLetterJobQuery().singleResult();
  assertNotNull(job);
  managementService.deleteDeadLetterJob(job.getId());
}
