@Deployment public void testStartToEnd(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("startToEnd");
  assertProcessEnded(processInstance.getId());
  assertTrue(processInstance.isEnded());
}
