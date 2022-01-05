public void testQueryByProcessDefinitionName(){
  assertEquals(12,runtimeService.createExecutionQuery().processDefinitionName(CONCURRENT_PROCESS_NAME).list().size());
  assertEquals(2,runtimeService.createExecutionQuery().processDefinitionName(SEQUENTIAL_PROCESS_NAME).list().size());
}
