@Deployment(resources={"org/activiti/engine/test/bpmn/gateway/EventBasedGatewayTest.testCatchAlertAndTimer.bpmn20.xml","org/activiti/engine/test/bpmn/gateway/EventBasedGatewayTest.throwAlertSignal.bpmn20.xml"}) public void testCatchSignalCancelsTimer(){
  ProcessInstance pi1=runtimeService.startProcessInstanceByKey("catchSignal");
  assertEquals(1,createEventSubscriptionQuery().count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  assertEquals(1,managementService.createTimerJobQuery().count());
  ProcessInstance pi2=runtimeService.startProcessInstanceByKey("throwSignal");
  assertEquals(0,createEventSubscriptionQuery().count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  assertEquals(0,managementService.createJobQuery().count());
  assertEquals(0,managementService.createTimerJobQuery().count());
  Task task=taskService.createTaskQuery().taskName("afterSignal").singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  assertHistoricActivitiesDeleteReason(pi1,DeleteReason.EVENT_BASED_GATEWAY_CANCEL,"timerEvent");
}
