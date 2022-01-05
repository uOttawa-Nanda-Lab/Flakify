@Deployment(resources="org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml") public void testProcessVariableValueEqualsIgnoreCase() throws Exception {
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("mixed","AzerTY");
  variables.put("upper","AZERTY");
  variables.put("lower","azerty");
  runtimeService.startProcessInstanceByKey("oneTaskProcess",variables);
  assertEquals(1,taskService.createTaskQuery().processVariableValueEqualsIgnoreCase("mixed","azerTY").count());
  assertEquals(1,taskService.createTaskQuery().processVariableValueEqualsIgnoreCase("mixed","azerty").count());
  assertEquals(0,taskService.createTaskQuery().processVariableValueEqualsIgnoreCase("mixed","uiop").count());
  assertEquals(1,taskService.createTaskQuery().processVariableValueEqualsIgnoreCase("upper","azerTY").count());
  assertEquals(1,taskService.createTaskQuery().processVariableValueEqualsIgnoreCase("upper","azerty").count());
  assertEquals(0,taskService.createTaskQuery().processVariableValueEqualsIgnoreCase("upper","uiop").count());
  assertEquals(1,taskService.createTaskQuery().processVariableValueEqualsIgnoreCase("lower","azerTY").count());
  assertEquals(1,taskService.createTaskQuery().processVariableValueEqualsIgnoreCase("lower","azerty").count());
  assertEquals(0,taskService.createTaskQuery().processVariableValueEqualsIgnoreCase("lower","uiop").count());
}
