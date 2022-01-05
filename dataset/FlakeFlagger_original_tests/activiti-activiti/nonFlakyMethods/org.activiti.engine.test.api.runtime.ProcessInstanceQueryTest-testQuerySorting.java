public void testQuerySorting(){
  assertEquals(PROCESS_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().orderByProcessInstanceId().asc().list().size());
  assertEquals(PROCESS_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().orderByProcessDefinitionId().asc().list().size());
  assertEquals(PROCESS_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().orderByProcessDefinitionKey().asc().list().size());
  assertEquals(PROCESS_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().orderByProcessInstanceId().desc().list().size());
  assertEquals(PROCESS_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().orderByProcessDefinitionId().desc().list().size());
  assertEquals(PROCESS_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().orderByProcessDefinitionKey().desc().list().size());
  assertEquals(PROCESS_DEFINITION_KEY_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).orderByProcessInstanceId().asc().list().size());
  assertEquals(PROCESS_DEFINITION_KEY_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).orderByProcessInstanceId().desc().list().size());
}
