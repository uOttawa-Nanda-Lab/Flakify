public void testJobCommandsWithMessage(){
  CommandExecutor commandExecutor=processEngineConfiguration.getCommandExecutor();
  String jobId=commandExecutor.execute(new Command<String>(){
    public String execute(    CommandContext commandContext){
      JobEntity message=createTweetMessage("i'm coding a test");
      commandContext.getJobManager().scheduleAsyncJob(message);
      return message.getId();
    }
  }
);
  Job job=managementService.createJobQuery().singleResult();
  assertNotNull(job);
  assertEquals(jobId,job.getId());
  assertEquals(0,tweetHandler.getMessages().size());
  managementService.executeJob(job.getId());
  assertEquals("i'm coding a test",tweetHandler.getMessages().get(0));
  assertEquals(1,tweetHandler.getMessages().size());
}
