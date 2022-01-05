@Deployment(resources={"org/activiti/engine/test/api/task/TaskVariablesTest.testTaskExecutionVariables.bpmn20.xml"}) public void testGetVariablesLocalByTaskIdsForSerializableType(){
  runtimeService.startProcessInstanceByKey("oneTaskProcess").getId();
  String taskId=taskService.createTaskQuery().singleResult().getId();
  StringBuilder sb=new StringBuilder("a");
  for (int i=0; i < 4001; i++) {
    sb.append("a");
  }
  String serializableTypeVar=sb.toString();
  taskService.setVariableLocal(taskId,"taskVar1",serializableTypeVar);
  Set<String> taskIds=new HashSet<String>();
  taskIds.add(taskId);
  List<VariableInstance> variables=taskService.getVariableInstancesLocalByTaskIds(taskIds);
  assertEquals(serializableTypeVar,variables.get(0).getValue());
}
