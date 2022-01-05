@Deployment public void testTaskResolveWithTransientVariables(){
  Map<String,Object> persistentVars=new HashMap<String,Object>();
  persistentVars.put("persistentVar1","Hello World");
  persistentVars.put("persistentVar2",987654321);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transientVarsTest",persistentVars);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("My Task",task.getName());
  persistentVars.clear();
  Map<String,Object> transientVars=new HashMap<String,Object>();
  transientVars.put("unusedTransientVar","Hello there");
  transientVars.put("transientVar","OK");
  taskService.complete(task.getId(),persistentVars,transientVars);
  String combinedVar=(String)runtimeService.getVariable(processInstance.getId(),"combinedVar");
  assertEquals("Hello WorldABC123",combinedVar);
  assertNotNull(runtimeService.getVariable(processInstance.getId(),"persistentVar1"));
  assertNotNull(runtimeService.getVariable(processInstance.getId(),"persistentVar2"));
  assertNull(runtimeService.getVariable(processInstance.getId(),"unusedTransientVar"));
  assertNull(runtimeService.getVariable(processInstance.getId(),"transientVar"));
  assertNull(runtimeService.getVariable(processInstance.getId(),"secondTransientVar"));
  assertNull(runtimeService.getVariable(processInstance.getId(),"thirdTransientVar"));
}
