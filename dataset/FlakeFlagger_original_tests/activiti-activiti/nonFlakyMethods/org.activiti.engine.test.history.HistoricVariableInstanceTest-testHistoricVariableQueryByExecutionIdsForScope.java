@Deployment(resources={"org/activiti/engine/test/api/runtime/variableScope.bpmn20.xml"}) public void testHistoricVariableQueryByExecutionIdsForScope(){
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
  for (  Task task : tasks) {
    taskService.setVariableLocal(task.getId(),"taskVar","taskVar");
  }
  Set<String> processInstanceIds=new HashSet<String>();
  processInstanceIds.add(processInstance.getId());
  List<HistoricVariableInstance> historicVariableInstances=historyService.createHistoricVariableInstanceQuery().executionIds(processInstanceIds).list();
  assertEquals(historicVariableInstances.size(),1);
  assertEquals(historicVariableInstances.get(0).getVariableName(),"processVar");
  assertEquals(historicVariableInstances.get(0).getValue(),"processVar");
  historicVariableInstances=historyService.createHistoricVariableInstanceQuery().executionIds(executionIds).excludeTaskVariables().list();
  assertEquals(historicVariableInstances.size(),2);
  assertEquals(historicVariableInstances.get(0).getVariableName(),"executionVar");
  assertEquals(historicVariableInstances.get(0).getValue(),"executionVar");
  assertEquals(historicVariableInstances.get(1).getVariableName(),"executionVar");
  assertEquals(historicVariableInstances.get(1).getValue(),"executionVar");
}
