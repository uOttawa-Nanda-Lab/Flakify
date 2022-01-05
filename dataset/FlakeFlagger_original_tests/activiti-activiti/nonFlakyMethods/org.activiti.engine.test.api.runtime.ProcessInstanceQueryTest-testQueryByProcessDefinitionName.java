public void testQueryByProcessDefinitionName(){
  assertEquals(PROCESS_DEFINITION_KEY_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().processDefinitionName(PROCESS_DEFINITION_NAME).count());
  assertEquals(PROCESS_DEFINITION_KEY_2_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().processDefinitionName(PROCESS_DEFINITION_NAME_2).count());
}
