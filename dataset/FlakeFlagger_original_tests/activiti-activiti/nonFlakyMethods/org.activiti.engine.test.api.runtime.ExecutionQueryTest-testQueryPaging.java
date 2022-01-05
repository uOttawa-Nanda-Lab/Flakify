public void testQueryPaging(){
  assertEquals(14,runtimeService.createExecutionQuery().count());
  assertEquals(4,runtimeService.createExecutionQuery().processDefinitionKey(CONCURRENT_PROCESS_KEY).listPage(0,4).size());
  assertEquals(1,runtimeService.createExecutionQuery().processDefinitionKey(CONCURRENT_PROCESS_KEY).listPage(2,1).size());
  assertEquals(10,runtimeService.createExecutionQuery().processDefinitionKey(CONCURRENT_PROCESS_KEY).listPage(1,10).size());
  assertEquals(12,runtimeService.createExecutionQuery().processDefinitionKey(CONCURRENT_PROCESS_KEY).listPage(0,20).size());
}
