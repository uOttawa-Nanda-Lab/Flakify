@Deployment(resources="org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml") public void testVariableValueEqualsIgnoreCase() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("mixed","AzerTY");
  variables.put("upper","AZERTY");
  variables.put("lower","azerty");
  taskService.setVariablesLocal(task.getId(),variables);
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEqualsIgnoreCase("mixed","azerTY").count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEqualsIgnoreCase("mixed","azerty").count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueEqualsIgnoreCase("mixed","uiop").count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEqualsIgnoreCase("upper","azerTY").count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEqualsIgnoreCase("upper","azerty").count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueEqualsIgnoreCase("upper","uiop").count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEqualsIgnoreCase("lower","azerTY").count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueEqualsIgnoreCase("lower","azerty").count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueEqualsIgnoreCase("lower","uiop").count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueNotEqualsIgnoreCase("mixed","azerTY").count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueNotEqualsIgnoreCase("mixed","azerty").count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueNotEqualsIgnoreCase("mixed","uiop").count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueNotEqualsIgnoreCase("upper","azerTY").count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueNotEqualsIgnoreCase("upper","azerty").count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueNotEqualsIgnoreCase("upper","uiop").count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueNotEqualsIgnoreCase("lower","azerTY").count());
  assertEquals(0,taskService.createTaskQuery().taskVariableValueNotEqualsIgnoreCase("lower","azerty").count());
  assertEquals(1,taskService.createTaskQuery().taskVariableValueNotEqualsIgnoreCase("lower","uiop").count());
}
