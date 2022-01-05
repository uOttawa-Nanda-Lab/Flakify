@Deployment(resources={"org/activiti/engine/test/api/runtime/variableScope.bpmn20.xml"}) public void testGetVariablesByExecutionIdsForScope(){
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
  List<VariableInstance> executionVariableInstances=runtimeService.getVariableInstancesByExecutionIds(executionIds);
  assertEquals(executionVariableInstances.size(),2);
  assertEquals(executionVariableInstances.get(0).getName(),"executionVar");
  assertEquals(executionVariableInstances.get(0).getValue(),"executionVar");
  assertEquals(executionVariableInstances.get(1).getName(),"executionVar");
  assertEquals(executionVariableInstances.get(1).getValue(),"executionVar");
  executionIds=new HashSet<String>();
  executionIds.add(processInstance.getId());
  executionVariableInstances=runtimeService.getVariableInstancesByExecutionIds(executionIds);
  assertEquals(executionVariableInstances.size(),1);
  assertEquals(executionVariableInstances.get(0).getName(),"processVar");
  assertEquals(executionVariableInstances.get(0).getValue(),"processVar");
}
