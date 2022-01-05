@Deployment public void testChangeExecutionCountSettingAndRebootengine(){
  rebootEngine(true);
  assertConfigProperty(true);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("twoTasksProcess");
  assertExecutions(processInstance,true);
  rebootEngine(true);
  assertConfigProperty(true);
  assertExecutions(processInstance,true);
  rebootEngine(false);
  assertConfigProperty(false);
  assertExecutions(processInstance,false);
  finishProcessInstance(processInstance);
  rebootEngine(false);
  assertConfigProperty(false);
  processInstance=runtimeService.startProcessInstanceByKey("twoTasksProcess");
  assertExecutions(processInstance,false);
  rebootEngine(true);
  assertConfigProperty(true);
  assertExecutions(processInstance,false);
  finishProcessInstance(processInstance);
  processInstance=runtimeService.startProcessInstanceByKey("twoTasksProcess");
  assertExecutions(processInstance,true);
  finishProcessInstance(processInstance);
}
