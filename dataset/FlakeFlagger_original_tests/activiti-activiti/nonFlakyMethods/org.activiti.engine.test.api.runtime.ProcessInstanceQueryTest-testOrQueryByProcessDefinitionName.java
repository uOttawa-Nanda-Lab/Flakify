public void testOrQueryByProcessDefinitionName(){
  assertEquals(PROCESS_DEFINITION_KEY_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().or().processDefinitionName(PROCESS_DEFINITION_NAME).processDefinitionId("undefined").endOr().count());
  assertEquals(PROCESS_DEFINITION_KEY_2_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().or().processDefinitionName(PROCESS_DEFINITION_NAME_2).processDefinitionId("undefined").endOr().count());
}
