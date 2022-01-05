/** 
 * Test to validate fix for ACT-1399: Boundary-event and event-based auditing
 */
@Deployment public void testEventBasedGateway(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("catchSignal");
  Execution waitingExecution=runtimeService.createExecutionQuery().signalEventSubscriptionName("alert").singleResult();
  assertNotNull(waitingExecution);
  runtimeService.signalEventReceived("alert",waitingExecution.getId());
  assertEquals(0L,runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).count());
  HistoricActivityInstance historicActivityInstance=historyService.createHistoricActivityInstanceQuery().activityId("eventBasedgateway").processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(historicActivityInstance);
}
