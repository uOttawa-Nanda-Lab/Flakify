/** 
 * Test +-->End1 Start-<> +-->End2 <p/> process on PROCESS_COMPLETED event
 */
@Deployment(resources={"org/activiti/engine/test/api/event/ProcessInstanceEventsTest.parallelGatewayTwoEndsProcess.bpmn20.xml"}) public void testProcessCompleted_ParallelGatewayTwoEnds() throws Exception {
  this.runtimeService.startProcessInstanceByKey("noEndProcess");
  List<ActivitiEvent> events=listener.filterEvents(ActivitiEventType.PROCESS_COMPLETED);
  assertEquals("ActivitiEventType.PROCESS_COMPLETED was expected 1 time.",1,events.size());
}
