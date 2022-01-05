@Deployment(resources={"org/activiti/examples/bpmn/executionlistener/ExecutionListenersStartEndEvent.bpmn20.xml"}) public void testExecutionListenersOnStartEndEvents(){
  RecorderExecutionListener.clear();
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("executionListenersProcess");
  assertProcessEnded(processInstance.getId());
  List<RecordedEvent> recordedEvents=RecorderExecutionListener.getRecordedEvents();
  assertEquals(4,recordedEvents.size());
  assertEquals("theStart",recordedEvents.get(0).getActivityId());
  assertEquals("Start Event",recordedEvents.get(0).getActivityName());
  assertEquals("Start Event Listener",recordedEvents.get(0).getParameter());
  assertEquals("end",recordedEvents.get(0).getEventName());
  assertEquals("noneEvent",recordedEvents.get(1).getActivityId());
  assertEquals("None Event",recordedEvents.get(1).getActivityName());
  assertEquals("Intermediate Catch Event Listener",recordedEvents.get(1).getParameter());
  assertEquals("end",recordedEvents.get(1).getEventName());
  assertEquals("signalEvent",recordedEvents.get(2).getActivityId());
  assertEquals("Signal Event",recordedEvents.get(2).getActivityName());
  assertEquals("Intermediate Throw Event Listener",recordedEvents.get(2).getParameter());
  assertEquals("start",recordedEvents.get(2).getEventName());
  assertEquals("theEnd",recordedEvents.get(3).getActivityId());
  assertEquals("End Event",recordedEvents.get(3).getActivityName());
  assertEquals("End Event Listener",recordedEvents.get(3).getParameter());
  assertEquals("start",recordedEvents.get(3).getEventName());
}
