public void testQueryPaging(){
  assertEquals(PROCESS_DEFINITION_KEY_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).count());
  assertEquals(2,runtimeService.createProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).listPage(0,2).size());
  assertEquals(3,runtimeService.createProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).listPage(1,3).size());
}
