@Deployment public void testTriggerWithTransientVars(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transientVarsTest");
  Execution executionInWait1=runtimeService.createExecutionQuery().activityId("wait1").singleResult();
  runtimeService.trigger(executionInWait1.getId(),CollectionUtil.singletonMap("persistentVar","persistentValue01"));
  Execution executionInWait2=runtimeService.createExecutionQuery().activityId("wait2").singleResult();
  runtimeService.trigger(executionInWait2.getId(),CollectionUtil.singletonMap("anotherPersistentVar","persistentValue02"),CollectionUtil.singletonMap("transientVar","transientValue"));
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  String result=(String)taskService.getVariable(task.getId(),"result");
  assertEquals("persistentValue02persistentValue01transientValue",result);
  assertNull(runtimeService.getVariable(processInstance.getId(),"transientVar"));
}
