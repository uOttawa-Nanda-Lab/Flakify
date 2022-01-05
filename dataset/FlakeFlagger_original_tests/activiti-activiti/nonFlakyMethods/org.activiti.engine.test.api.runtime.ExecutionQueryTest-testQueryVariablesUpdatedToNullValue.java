@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testQueryVariablesUpdatedToNullValue(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("longVar",928374L);
  variables.put("shortVar",(short)123);
  variables.put("integerVar",1234);
  variables.put("stringVar","coca-cola");
  variables.put("booleanVar",true);
  variables.put("dateVar",new Date());
  variables.put("nullVar",null);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",variables);
  ExecutionQuery query=runtimeService.createExecutionQuery().variableValueEquals("longVar",null).variableValueEquals("shortVar",null).variableValueEquals("integerVar",null).variableValueEquals("stringVar",null).variableValueEquals("booleanVar",null).variableValueEquals("dateVar",null);
  ExecutionQuery notQuery=runtimeService.createExecutionQuery().variableValueNotEquals("longVar",null).variableValueNotEquals("shortVar",null).variableValueNotEquals("integerVar",null).variableValueNotEquals("stringVar",null).variableValueNotEquals("booleanVar",null).variableValueNotEquals("dateVar",null);
  assertNull(query.singleResult());
  assertNotNull(notQuery.singleResult());
  runtimeService.setVariable(processInstance.getId(),"longVar",null);
  runtimeService.setVariable(processInstance.getId(),"shortVar",null);
  runtimeService.setVariable(processInstance.getId(),"integerVar",null);
  runtimeService.setVariable(processInstance.getId(),"stringVar",null);
  runtimeService.setVariable(processInstance.getId(),"booleanVar",null);
  runtimeService.setVariable(processInstance.getId(),"dateVar",null);
  runtimeService.setVariable(processInstance.getId(),"nullVar",null);
  Execution queryResult=query.singleResult();
  assertNotNull(queryResult);
  assertEquals(processInstance.getId(),queryResult.getId());
  assertNull(notQuery.singleResult());
}
