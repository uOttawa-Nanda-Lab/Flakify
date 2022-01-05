@Deployment(resources={"org/activiti/engine/test/api/mgmt/timerOnTask.bpmn20.xml"}) public void testDeleteJobDeletion(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("timerOnTask");
  Job timerJob=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull("Task timer should be there",timerJob);
  managementService.deleteTimerJob(timerJob.getId());
  timerJob=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNull("There should be no job now. It was deleted",timerJob);
}
