@Deployment(resources="org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml") public void testProcessVariableValueLike() throws Exception {
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("mixed","AzerTY");
  runtimeService.startProcessInstanceByKey("oneTaskProcess",variables);
  assertEquals(1,taskService.createTaskQuery().processVariableValueLike("mixed","Azer%").count());
  assertEquals(1,taskService.createTaskQuery().processVariableValueLike("mixed","A%").count());
  assertEquals(0,taskService.createTaskQuery().processVariableValueLike("mixed","a%").count());
}
