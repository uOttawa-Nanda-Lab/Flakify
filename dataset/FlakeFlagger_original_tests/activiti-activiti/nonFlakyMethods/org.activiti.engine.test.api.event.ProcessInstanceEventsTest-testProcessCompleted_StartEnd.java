/** 
 * Test Start->End process on PROCESS_COMPLETED event
 */
@Deployment(resources={"org/activiti/engine/test/api/event/ProcessInstanceEventsTest.noneTaskProcess.bpmn20.xml"}) public void testProcessCompleted_StartEnd() throws Exception {
  this.runtimeService.startProcessInstanceByKey("noneTaskProcess");
  assertEquals("ActivitiEventType.PROCESS_COMPLETED was expected 1 time.",1,listener.filterEvents(ActivitiEventType.PROCESS_COMPLETED).size());
}
