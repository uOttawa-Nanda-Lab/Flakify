@Deployment(resources={"org/activiti/engine/test/bpmn/gateway/EventBasedGatewayTest.testCatchAlertAndTimer.bpmn20.xml"}) public void testCatchTimerCancelsSignal(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("catchSignal");
  assertEquals(1,createEventSubscriptionQuery().count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  assertEquals(1,managementService.createTimerJobQuery().count());
  processEngineConfiguration.getClock().setCurrentTime(new Date(processEngineConfiguration.getClock().getCurrentTime().getTime() + 10000));
  waitForJobExecutorToProcessAllJobs(10000,100);
  assertEquals(0,createEventSubscriptionQuery().count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  assertEquals(0,managementService.createJobQuery().count());
  assertEquals(0,managementService.createTimerJobQuery().count());
  Task task=taskService.createTaskQuery().taskName("afterTimer").singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  assertHistoricActivitiesDeleteReason(processInstance,DeleteReason.EVENT_BASED_GATEWAY_CANCEL,"signalEvent");
}
