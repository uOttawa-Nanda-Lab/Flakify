public void testQueryByBusinessKeyIncludingChildExecutions(){
  assertEquals(3,runtimeService.createExecutionQuery().processDefinitionKey(CONCURRENT_PROCESS_KEY).processInstanceBusinessKey("BUSINESS-KEY-1",true).list().size());
  assertEquals(3,runtimeService.createExecutionQuery().processDefinitionKey(CONCURRENT_PROCESS_KEY).processInstanceBusinessKey("BUSINESS-KEY-2",true).list().size());
  assertEquals(0,runtimeService.createExecutionQuery().processDefinitionKey(CONCURRENT_PROCESS_KEY).processInstanceBusinessKey("NON-EXISTING",true).list().size());
}
