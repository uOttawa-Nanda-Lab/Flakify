@Deployment public void testTransientVariableShadowsPersistentVariable(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transientVarsTest",CollectionUtil.singletonMap("theVar","Hello World"));
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  String varValue=(String)taskService.getVariable(task.getId(),"resultVar");
  assertEquals("I am shadowed",varValue);
}
