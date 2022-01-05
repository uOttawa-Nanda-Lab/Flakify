@Deployment public void testEarlyFinishedProcess(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("callerProcess");
  assertNotNull(processInstance.getId());
}
