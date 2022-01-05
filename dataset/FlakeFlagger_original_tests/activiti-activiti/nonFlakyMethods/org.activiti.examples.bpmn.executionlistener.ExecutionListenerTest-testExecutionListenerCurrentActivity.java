@Deployment(resources={"org/activiti/examples/bpmn/executionlistener/ExecutionListenersCurrentActivity.bpmn20.xml"}) public void testExecutionListenerCurrentActivity(){
  CurrentActivityExecutionListener.clear();
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("executionListenersProcess");
  assertProcessEnded(processInstance.getId());
  List<CurrentActivity> currentActivities=CurrentActivityExecutionListener.getCurrentActivities();
  assertEquals(3,currentActivities.size());
  assertEquals("theStart",currentActivities.get(0).getActivityId());
  assertEquals("Start Event",currentActivities.get(0).getActivityName());
  assertEquals("noneEvent",currentActivities.get(1).getActivityId());
  assertEquals("None Event",currentActivities.get(1).getActivityName());
  assertEquals("theEnd",currentActivities.get(2).getActivityId());
  assertEquals("End Event",currentActivities.get(2).getActivityName());
}
