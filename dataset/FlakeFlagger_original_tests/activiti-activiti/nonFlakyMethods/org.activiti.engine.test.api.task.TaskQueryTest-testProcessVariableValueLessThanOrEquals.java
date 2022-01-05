@Deployment(resources="org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml") public void testProcessVariableValueLessThanOrEquals() throws Exception {
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("number",10);
  runtimeService.startProcessInstanceByKey("oneTaskProcess",variables);
  assertEquals(1,taskService.createTaskQuery().processVariableValueLessThanOrEqual("number",12).count());
  assertEquals(1,taskService.createTaskQuery().processVariableValueLessThanOrEqual("number",10).count());
  assertEquals(0,taskService.createTaskQuery().processVariableValueLessThanOrEqual("number",8).count());
}
