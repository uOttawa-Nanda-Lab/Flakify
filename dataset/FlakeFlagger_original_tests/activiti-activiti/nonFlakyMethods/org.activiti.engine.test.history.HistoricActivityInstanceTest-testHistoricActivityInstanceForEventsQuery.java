@Deployment public void testHistoricActivityInstanceForEventsQuery(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("eventProcess");
  assertEquals(1,taskService.createTaskQuery().count());
  runtimeService.signalEventReceived("signal");
  assertProcessEnded(pi.getId());
  assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("noop").list().size());
  assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("userTask").list().size());
  assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("intermediate-event").list().size());
  assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("start").list().size());
  assertEquals(1,historyService.createHistoricActivityInstanceQuery().activityId("end").list().size());
  HistoricActivityInstance intermediateEvent=historyService.createHistoricActivityInstanceQuery().activityId("intermediate-event").singleResult();
  assertNotNull(intermediateEvent.getStartTime());
  assertNotNull(intermediateEvent.getEndTime());
  HistoricActivityInstance startEvent=historyService.createHistoricActivityInstanceQuery().activityId("start").singleResult();
  assertNotNull(startEvent.getStartTime());
  assertNotNull(startEvent.getEndTime());
  HistoricActivityInstance endEvent=historyService.createHistoricActivityInstanceQuery().activityId("end").singleResult();
  assertNotNull(endEvent.getStartTime());
  assertNotNull(endEvent.getEndTime());
}
