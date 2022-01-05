/** 
 * Test process with signals start.
 */
@Deployment(resources={"org/activiti/engine/test/bpmn/event/signal/SignalEventTest.testSignalWithGlobalScope.bpmn20.xml"}) public void testSignalProcessInstanceStart() throws Exception {
  this.runtimeService.startProcessInstanceByKey("processWithSignalCatch");
  listener.clearEventsReceived();
  runtimeService.startProcessInstanceByKey("processWithSignalThrow");
  listener.clearEventsReceived();
}
