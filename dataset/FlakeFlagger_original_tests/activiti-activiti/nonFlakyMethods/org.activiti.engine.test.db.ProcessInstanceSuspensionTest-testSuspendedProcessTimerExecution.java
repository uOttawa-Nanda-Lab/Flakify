@Deployment public void testSuspendedProcessTimerExecution() throws Exception {
  ProcessInstance procInst=runtimeService.startProcessInstanceByKey("suspendProcess");
  assertNotNull(procInst);
  assertEquals(1,managementService.createTimerJobQuery().processInstanceId(procInst.getId()).count());
  Calendar tomorrow=Calendar.getInstance();
  tomorrow.add(Calendar.DAY_OF_YEAR,1);
  processEngineConfiguration.getClock().setCurrentTime(tomorrow.getTime());
  CommandExecutor commandExecutor=processEngineConfiguration.getCommandExecutor();
  List<TimerJobEntity> jobs=commandExecutor.execute(new Command<List<TimerJobEntity>>(){
    @Override public List<TimerJobEntity> execute(    CommandContext commandContext){
      return processEngineConfiguration.getTimerJobEntityManager().findTimerJobsToExecute(new Page(0,1));
    }
  }
);
  assertEquals(1,jobs.size());
  runtimeService.suspendProcessInstanceById(procInst.getId());
  jobs=commandExecutor.execute(new Command<List<TimerJobEntity>>(){
    @Override public List<TimerJobEntity> execute(    CommandContext commandContext){
      return processEngineConfiguration.getTimerJobEntityManager().findTimerJobsToExecute(new Page(0,1));
    }
  }
);
  assertEquals(0,jobs.size());
}
