@Deployment(resources={"org/activiti/engine/test/api/mgmt/timerOnTask.bpmn20.xml"}) public void testDeleteJobThatWasAlreadyAcquired(){
  processEngineConfiguration.getClock().setCurrentTime(new Date());
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("timerOnTask");
  Job timerJob=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();
  processEngineConfiguration.getClock().setCurrentTime(new Date(processEngineConfiguration.getClock().getCurrentTime().getTime() + 7200000L));
  ProcessEngineImpl processEngineImpl=(ProcessEngineImpl)processEngine;
  AcquireTimerJobsCmd acquireJobsCmd=new AcquireTimerJobsCmd(processEngine.getProcessEngineConfiguration().getAsyncExecutor());
  CommandExecutor commandExecutor=processEngineImpl.getProcessEngineConfiguration().getCommandExecutor();
  commandExecutor.execute(acquireJobsCmd);
  try {
    managementService.deleteJob(timerJob.getId());
    fail();
  }
 catch (  ActivitiException e) {
  }
  managementService.moveTimerToExecutableJob(timerJob.getId());
  managementService.executeJob(timerJob.getId());
}
