/** 
 * Test +-->Task1 Start-<> +-->Task1 process on PROCESS_COMPLETED event
 */
@Deployment(resources={"org/activiti/engine/test/api/event/ProcessInstanceEventsTest.parallelGatewayNoEndProcess.bpmn20.xml"}) public void testProcessCompleted_ParallelGatewayNoEnd() throws Exception {
  this.runtimeService.startProcessInstanceByKey("noEndProcess");
  assertEquals("ActivitiEventType.PROCESS_COMPLETED was expected 1 time.",1,listener.filterEvents(ActivitiEventType.PROCESS_COMPLETED).size());
}
