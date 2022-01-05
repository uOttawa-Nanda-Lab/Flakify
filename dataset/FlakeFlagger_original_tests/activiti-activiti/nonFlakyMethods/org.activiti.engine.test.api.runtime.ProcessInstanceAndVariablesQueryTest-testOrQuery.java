public void testOrQuery(){
  ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("undefined",999).variableValueEquals("anothertest",123).endOr().singleResult();
  Map<String,Object> variableMap=processInstance.getProcessVariables();
  assertEquals(1,variableMap.size());
  assertEquals(123,variableMap.get("anothertest"));
  processInstance=runtimeService.createProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("undefined",999).endOr().singleResult();
  assertNull(processInstance);
  processInstance=runtimeService.createProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest",123).variableValueEquals("undefined",999).endOr().singleResult();
  variableMap=processInstance.getProcessVariables();
  assertEquals(1,variableMap.size());
  assertEquals(123,variableMap.get("anothertest"));
  processInstance=runtimeService.createProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest",999).endOr().singleResult();
  assertNull(processInstance);
  processInstance=runtimeService.createProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest",999).variableValueEquals("anothertest",123).endOr().singleResult();
  variableMap=processInstance.getProcessVariables();
  assertEquals(1,variableMap.size());
  assertEquals(123,variableMap.get("anothertest"));
}
