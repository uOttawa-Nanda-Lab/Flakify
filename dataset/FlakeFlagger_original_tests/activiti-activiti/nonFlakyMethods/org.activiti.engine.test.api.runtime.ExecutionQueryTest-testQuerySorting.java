public void testQuerySorting(){
  assertEquals(14,runtimeService.createExecutionQuery().orderByProcessInstanceId().asc().list().size());
  assertEquals(14,runtimeService.createExecutionQuery().orderByProcessDefinitionId().asc().list().size());
  assertEquals(14,runtimeService.createExecutionQuery().orderByProcessDefinitionKey().asc().list().size());
  assertEquals(14,runtimeService.createExecutionQuery().orderByProcessInstanceId().desc().list().size());
  assertEquals(14,runtimeService.createExecutionQuery().orderByProcessDefinitionId().desc().list().size());
  assertEquals(14,runtimeService.createExecutionQuery().orderByProcessDefinitionKey().desc().list().size());
  assertEquals(12,runtimeService.createExecutionQuery().processDefinitionKey(CONCURRENT_PROCESS_KEY).orderByProcessDefinitionId().asc().list().size());
  assertEquals(12,runtimeService.createExecutionQuery().processDefinitionKey(CONCURRENT_PROCESS_KEY).orderByProcessDefinitionId().desc().list().size());
  assertEquals(12,runtimeService.createExecutionQuery().processDefinitionKey(CONCURRENT_PROCESS_KEY).orderByProcessDefinitionKey().asc().orderByProcessInstanceId().desc().list().size());
}
