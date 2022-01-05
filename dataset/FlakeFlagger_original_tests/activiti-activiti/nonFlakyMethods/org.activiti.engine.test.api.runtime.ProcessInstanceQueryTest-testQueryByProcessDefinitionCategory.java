public void testQueryByProcessDefinitionCategory(){
  assertEquals(PROCESS_DEFINITION_KEY_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().processDefinitionCategory(PROCESS_DEFINITION_CATEGORY).count());
  assertEquals(PROCESS_DEFINITION_KEY_2_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().processDefinitionCategory(PROCESS_DEFINITION_CATEGORY_2).count());
}
