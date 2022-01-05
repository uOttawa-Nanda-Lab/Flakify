public void testOrQueryMultipleVariableValues(){
  ProcessInstanceQuery query0=runtimeService.createProcessInstanceQuery().includeProcessVariables().or();
  for (int i=0; i < 20; i++) {
    query0=query0.variableValueEquals("anothertest",i);
  }
  query0=query0.endOr();
  assertNull(query0.singleResult());
  ProcessInstanceQuery query1=runtimeService.createProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest",123);
  for (int i=0; i < 20; i++) {
    query1=query1.variableValueEquals("anothertest",i);
  }
  query1=query1.endOr();
  assertNull(query0.singleResult());
  ProcessInstance processInstance=query1.singleResult();
  Map<String,Object> variableMap=processInstance.getProcessVariables();
  assertEquals(1,variableMap.size());
  assertEquals(123,variableMap.get("anothertest"));
  ProcessInstanceQuery query2=runtimeService.createProcessInstanceQuery().includeProcessVariables().or();
  for (int i=0; i < 20; i++) {
    query2=query2.variableValueEquals("anothertest",i);
  }
  query2=query2.endOr().or().processDefinitionKey(PROCESS_DEFINITION_KEY_2).processDefinitionId("undefined").endOr();
  assertNull(query2.singleResult());
  ProcessInstanceQuery query3=runtimeService.createProcessInstanceQuery().includeProcessVariables().or().variableValueEquals("anothertest",123);
  for (int i=0; i < 20; i++) {
    query3=query3.variableValueEquals("anothertest",i);
  }
  query3=query3.endOr().or().processDefinitionKey(PROCESS_DEFINITION_KEY_2).processDefinitionId("undefined").endOr();
  variableMap=query3.singleResult().getProcessVariables();
  assertEquals(1,variableMap.size());
  assertEquals(123,variableMap.get("anothertest"));
}
