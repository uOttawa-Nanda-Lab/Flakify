@Deployment(resources={"org/activiti/engine/test/api/runtime/variableScope.bpmn20.xml"}) public void testHistoricVariableQueryByTaskIdsForScope(){
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
  List<HistoricVariableInstance> historicVariableInstances=historyService.createHistoricVariableInstanceQuery().taskIds(taskIds).list();
  assertEquals(historicVariableInstances.size(),2);
  assertEquals(historicVariableInstances.get(0).getVariableName(),"taskVar");
  assertEquals(historicVariableInstances.get(0).getValue(),"taskVar");
  assertEquals(historicVariableInstances.get(1).getVariableName(),"taskVar");
  assertEquals(historicVariableInstances.get(1).getValue(),"taskVar");
}
