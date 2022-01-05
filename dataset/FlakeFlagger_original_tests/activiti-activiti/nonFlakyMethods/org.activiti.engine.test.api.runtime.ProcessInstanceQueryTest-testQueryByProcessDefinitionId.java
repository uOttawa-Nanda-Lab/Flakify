public void testQueryByProcessDefinitionId(){
  final ProcessDefinition processDefinition1=repositoryService.createProcessDefinitionQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).singleResult();
  ProcessInstanceQuery query1=runtimeService.createProcessInstanceQuery().processDefinitionId(processDefinition1.getId());
  assertEquals(PROCESS_DEFINITION_KEY_DEPLOY_COUNT,query1.count());
  assertEquals(PROCESS_DEFINITION_KEY_DEPLOY_COUNT,query1.list().size());
  try {
    query1.singleResult();
    fail();
  }
 catch (  ActivitiException e) {
  }
  final ProcessDefinition processDefinition2=repositoryService.createProcessDefinitionQuery().processDefinitionKey(PROCESS_DEFINITION_KEY_2).singleResult();
  ProcessInstanceQuery query2=runtimeService.createProcessInstanceQuery().processDefinitionId(processDefinition2.getId());
  assertEquals(PROCESS_DEFINITION_KEY_2_DEPLOY_COUNT,query2.count());
  assertEquals(PROCESS_DEFINITION_KEY_2_DEPLOY_COUNT,query2.list().size());
  assertNotNull(query2.singleResult());
}
