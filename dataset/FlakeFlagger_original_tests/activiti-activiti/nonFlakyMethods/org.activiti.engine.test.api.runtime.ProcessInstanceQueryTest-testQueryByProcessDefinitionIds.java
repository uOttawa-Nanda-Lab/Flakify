public void testQueryByProcessDefinitionIds(){
  final ProcessDefinition processDefinition1=repositoryService.createProcessDefinitionQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).singleResult();
  final ProcessDefinition processDefinition2=repositoryService.createProcessDefinitionQuery().processDefinitionKey(PROCESS_DEFINITION_KEY_2).singleResult();
  final Set<String> processDefinitionIdSet=new HashSet<String>(2);
  processDefinitionIdSet.add(processDefinition1.getId());
  processDefinitionIdSet.add(processDefinition2.getId());
  ProcessInstanceQuery query=runtimeService.createProcessInstanceQuery().processDefinitionIds(processDefinitionIdSet);
  assertEquals(PROCESS_DEPLOY_COUNT,query.count());
  assertEquals(PROCESS_DEPLOY_COUNT,query.list().size());
  try {
    query.singleResult();
    fail();
  }
 catch (  ActivitiException e) {
  }
}
