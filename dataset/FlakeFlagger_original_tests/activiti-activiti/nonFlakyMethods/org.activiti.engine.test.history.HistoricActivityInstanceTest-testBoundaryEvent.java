/** 
 * Test to validate fix for ACT-1399: Boundary-event and event-based auditing
 */
@Deployment public void testBoundaryEvent(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("boundaryEventProcess");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  assertEquals(0L,runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).count());
  HistoricActivityInstance historicActivityInstance=historyService.createHistoricActivityInstanceQuery().activityId("boundary").processInstanceId(processInstance.getId()).singleResult();
  assertNull(historicActivityInstance);
  processInstance=runtimeService.startProcessInstanceByKey("boundaryEventProcess");
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  Execution signalExecution=runtimeService.createExecutionQuery().signalEventSubscriptionName("alert").singleResult();
  runtimeService.signalEventReceived("alert",signalExecution.getId());
  assertEquals(0L,runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).count());
  historicActivityInstance=historyService.createHistoricActivityInstanceQuery().activityId("boundary").processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(historicActivityInstance);
  assertNotNull(historicActivityInstance.getStartTime());
  assertNotNull(historicActivityInstance.getEndTime());
}
