public void testOrQueryByProcessDefinitionCategory(){
  assertEquals(PROCESS_DEFINITION_KEY_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().or().processDefinitionCategory(PROCESS_DEFINITION_CATEGORY).processDefinitionId("undefined").endOr().count());
  assertEquals(PROCESS_DEFINITION_KEY_2_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().or().processDefinitionCategory(PROCESS_DEFINITION_CATEGORY_2).processDefinitionId("undefined").endOr().count());
}
