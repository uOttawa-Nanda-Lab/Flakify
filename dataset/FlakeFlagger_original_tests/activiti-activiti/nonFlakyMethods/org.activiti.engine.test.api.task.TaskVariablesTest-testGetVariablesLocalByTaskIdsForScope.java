@Deployment(resources={"org/activiti/engine/test/api/runtime/variableScope.bpmn20.xml"}) public void testGetVariablesLocalByTaskIdsForScope(){
  Map<String,Object> processVars=new HashMap<String,Object>();
  processVars.put("processVar","processVar");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("variableScopeProcess",processVars);
  Set<String> executionIds=new HashSet<String>();
  List<Execution> executions=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list();
  for (  Execution execution : executions) {
    if (!processInstance.getId().equals(execution.getId())) {
      executionIds.add(execution.getId());
      runtimeService.setVariableLocal(execution.getId(),"executionVar","executionVar");
    }
  }
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  Set<String> taskIds=new HashSet<String>();
  for (  Task task : tasks) {
    taskService.setVariableLocal(task.getId(),"taskVar","taskVar");
    taskIds.add(task.getId());
  }
  List<VariableInstance> variableInstances=taskService.getVariableInstancesLocalByTaskIds(taskIds);
  assertEquals(variableInstances.size(),2);
  assertEquals(variableInstances.get(0).getName(),"taskVar");
  assertEquals(variableInstances.get(0).getValue(),"taskVar");
  assertEquals(variableInstances.get(1).getName(),"taskVar");
  assertEquals(variableInstances.get(1).getValue(),"taskVar");
}
