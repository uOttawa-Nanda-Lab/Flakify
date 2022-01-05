@Deployment(resources={"org/activiti/engine/test/api/runtime/RuntimeVariablesTest.testGetVariablesByExecutionIds.bpmn20.xml"}) public void testGetVariablesByExecutionIdsForSerializableType(){
  ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task task1=taskService.createTaskQuery().processInstanceId(processInstance1.getId()).singleResult();
  StringBuilder sb=new StringBuilder("a");
  for (int i=0; i < 4001; i++) {
    sb.append("a");
  }
  String serializableTypeVar=sb.toString();
  taskService.setVariable(task1.getId(),"executionVar1",serializableTypeVar);
  Set<String> executionIds=new HashSet<String>();
  executionIds.add(processInstance1.getId());
  List<VariableInstance> variables=runtimeService.getVariableInstancesByExecutionIds(executionIds);
  assertEquals(serializableTypeVar,variables.get(0).getValue());
}
