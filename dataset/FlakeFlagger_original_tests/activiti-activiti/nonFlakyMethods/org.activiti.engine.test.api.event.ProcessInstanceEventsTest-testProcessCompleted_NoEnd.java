/** 
 * Test Start->User Task process on PROCESS_COMPLETED event
 */
@Deployment(resources={"org/activiti/engine/test/api/event/ProcessInstanceEventsTest.noEndProcess.bpmn20.xml"}) public void testProcessCompleted_NoEnd() throws Exception {
  ProcessInstance noEndProcess=this.runtimeService.startProcessInstanceByKey("noEndProcess");
  Task task=taskService.createTaskQuery().processInstanceId(noEndProcess.getId()).singleResult();
  taskService.complete(task.getId());
  assertEquals("ActivitiEventType.PROCESS_COMPLETED was expected 1 time.",1,listener.filterEvents(ActivitiEventType.PROCESS_COMPLETED).size());
}
