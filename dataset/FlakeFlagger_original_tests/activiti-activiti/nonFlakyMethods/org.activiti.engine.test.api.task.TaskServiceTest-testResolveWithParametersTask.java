@Deployment(resources={"org/activiti/engine/test/api/twoTasksProcess.bpmn20.xml"}) public void testResolveWithParametersTask(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("twoTasksProcess");
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("First task",task.getName());
  taskService.delegateTask(task.getId(),"johndoe");
  Map<String,Object> taskParams=new HashMap<String,Object>();
  taskParams.put("myParam","myValue");
  taskService.resolveTask(task.getId(),taskParams);
  task=taskService.createTaskQuery().taskDelegationState(DelegationState.RESOLVED).singleResult();
  assertEquals("First task",task.getName());
  Map<String,Object> variables=runtimeService.getVariables(processInstance.getId());
  assertEquals(1,variables.size());
  assertEquals("myValue",variables.get("myParam"));
}
