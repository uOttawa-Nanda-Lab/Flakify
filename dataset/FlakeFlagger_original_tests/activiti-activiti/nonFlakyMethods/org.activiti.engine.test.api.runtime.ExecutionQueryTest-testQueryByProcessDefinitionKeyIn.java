public void testQueryByProcessDefinitionKeyIn(){
  Set<String> includeIds=new HashSet<String>();
  assertEquals(14,runtimeService.createExecutionQuery().processDefinitionKeys(includeIds).list().size());
  includeIds.add(CONCURRENT_PROCESS_KEY);
  assertEquals(12,runtimeService.createExecutionQuery().processDefinitionKeys(includeIds).list().size());
  includeIds.add(SEQUENTIAL_PROCESS_KEY);
  assertEquals(14,runtimeService.createExecutionQuery().processDefinitionKeys(includeIds).list().size());
  includeIds.add("invalid");
  assertEquals(14,runtimeService.createExecutionQuery().processDefinitionKeys(includeIds).list().size());
  includeIds.clear();
  includeIds.add("invalid");
  assertEquals(0,runtimeService.createExecutionQuery().processDefinitionKeys(includeIds).list().size());
}
