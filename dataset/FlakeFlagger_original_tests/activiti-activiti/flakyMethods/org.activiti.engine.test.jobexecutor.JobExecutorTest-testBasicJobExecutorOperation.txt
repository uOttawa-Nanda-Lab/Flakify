public void testBasicJobExecutorOperation() throws Exception {
  CommandExecutor commandExecutor=processEngineConfiguration.getCommandExecutor();
  commandExecutor.execute(new Command<Void>(){
    public Void execute(    CommandContext commandContext){
      JobManager jobManager=commandContext.getJobManager();
      jobManager.execute(createTweetMessage("message-one"));
      jobManager.execute(createTweetMessage("message-two"));
      jobManager.execute(createTweetMessage("message-three"));
      jobManager.execute(createTweetMessage("message-four"));
      TimerJobEntityManager timerJobManager=commandContext.getTimerJobEntityManager();
      timerJobManager.insert(createTweetTimer("timer-one",new Date()));
      timerJobManager.insert(createTweetTimer("timer-one",new Date()));
      timerJobManager.insert(createTweetTimer("timer-two",new Date()));
      return null;
    }
  }
);
  GregorianCalendar currentCal=new GregorianCalendar();
  currentCal.add(Calendar.MINUTE,1);
  processEngineConfiguration.getClock().setCurrentTime(currentCal.getTime());
  waitForJobExecutorToProcessAllJobs(8000L,200L);
  Set<String> messages=new HashSet<String>(tweetHandler.getMessages());
  Set<String> expectedMessages=new HashSet<String>();
  expectedMessages.add("message-one");
  expectedMessages.add("message-two");
  expectedMessages.add("message-three");
  expectedMessages.add("message-four");
  expectedMessages.add("timer-one");
  expectedMessages.add("timer-two");
  assertEquals(new TreeSet<String>(expectedMessages),new TreeSet<String>(messages));
}
