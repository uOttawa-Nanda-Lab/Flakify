@Deployment public void testSetProcessVariablesFromTaskWhenTimerOnTask(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("timerVariablesProcess");
  runtimeService.setVariable(processInstance.getId(),"myVar",123456L);
}
