@Deployment public void testOrQuery(){
  Map<String,Object> startMap=new HashMap<String,Object>();
  startMap.put("anotherProcessVar",123);
  runtimeService.startProcessInstanceByKey("oneTaskProcess",startMap);
  Task task=taskService.createTaskQuery().includeProcessVariables().or().processVariableValueEquals("undefined",999).processVariableValueEquals("anotherProcessVar",123).endOr().singleResult();
  assertEquals(1,task.getProcessVariables().size());
  assertEquals(123,task.getProcessVariables().get("anotherProcessVar"));
  task=taskService.createTaskQuery().includeProcessVariables().or().processVariableValueEquals("undefined",999).endOr().singleResult();
  assertNull(task);
  task=taskService.createTaskQuery().includeProcessVariables().or().processVariableValueEquals("anotherProcessVar",123).processVariableValueEquals("undefined",999).endOr().singleResult();
  assertEquals(1,task.getProcessVariables().size());
  assertEquals(123,task.getProcessVariables().get("anotherProcessVar"));
  task=taskService.createTaskQuery().includeProcessVariables().or().processVariableValueEquals("anotherProcessVar",123).endOr().singleResult();
  assertEquals(1,task.getProcessVariables().size());
  assertEquals(123,task.getProcessVariables().get("anotherProcessVar"));
  task=taskService.createTaskQuery().includeProcessVariables().or().processVariableValueEquals("anotherProcessVar",999).endOr().singleResult();
  assertNull(task);
  task=taskService.createTaskQuery().includeProcessVariables().or().processVariableValueEquals("anotherProcessVar",999).processVariableValueEquals("anotherProcessVar",123).endOr().singleResult();
  assertEquals(1,task.getProcessVariables().size());
  assertEquals(123,task.getProcessVariables().get("anotherProcessVar"));
}
