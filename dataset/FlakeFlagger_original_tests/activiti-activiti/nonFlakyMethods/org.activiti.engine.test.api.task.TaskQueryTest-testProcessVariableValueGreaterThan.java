@Deployment(resources="org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml") public void testProcessVariableValueGreaterThan() throws Exception {
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("number",10);
  runtimeService.startProcessInstanceByKey("oneTaskProcess",variables);
  assertEquals(1,taskService.createTaskQuery().processVariableValueGreaterThan("number",5).count());
  assertEquals(0,taskService.createTaskQuery().processVariableValueGreaterThan("number",10).count());
}
