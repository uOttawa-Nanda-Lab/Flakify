@Test @org.activiti.engine.test.Deployment public void testScriptTask(){
  Map<String,Object> variableMap=new HashMap<String,Object>();
  variableMap.put("a",1);
  variableMap.put("b",2);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",variableMap);
  assertNotNull(processInstance);
  assertFalse(processInstance.isEnded());
  Number sumVariable=(Number)runtimeService.getVariable(processInstance.getId(),"sum");
  assertEquals(3,sumVariable.intValue());
  Execution execution=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).onlyChildExecutions().singleResult();
  assertNotNull(execution);
  runtimeService.trigger(execution.getId());
  assertNull(runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult());
}
