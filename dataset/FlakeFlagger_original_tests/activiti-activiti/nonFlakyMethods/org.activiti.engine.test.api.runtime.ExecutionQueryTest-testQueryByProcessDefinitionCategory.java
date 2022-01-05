public void testQueryByProcessDefinitionCategory(){
  assertEquals(12,runtimeService.createExecutionQuery().processDefinitionCategory(CONCURRENT_PROCESS_CATEGORY).list().size());
  assertEquals(2,runtimeService.createExecutionQuery().processDefinitionCategory(SEQUENTIAL_PROCESS_CATEGORY).list().size());
}
