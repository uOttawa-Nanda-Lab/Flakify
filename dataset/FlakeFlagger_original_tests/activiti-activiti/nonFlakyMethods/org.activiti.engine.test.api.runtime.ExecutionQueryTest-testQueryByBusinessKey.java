public void testQueryByBusinessKey(){
  assertEquals(1,runtimeService.createExecutionQuery().processDefinitionKey(CONCURRENT_PROCESS_KEY).processInstanceBusinessKey("BUSINESS-KEY-1").list().size());
  assertEquals(1,runtimeService.createExecutionQuery().processDefinitionKey(CONCURRENT_PROCESS_KEY).processInstanceBusinessKey("BUSINESS-KEY-2").list().size());
  assertEquals(0,runtimeService.createExecutionQuery().processDefinitionKey(CONCURRENT_PROCESS_KEY).processInstanceBusinessKey("NON-EXISTING").list().size());
}
