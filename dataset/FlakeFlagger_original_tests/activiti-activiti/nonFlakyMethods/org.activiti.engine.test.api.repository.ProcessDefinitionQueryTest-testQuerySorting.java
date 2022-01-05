public void testQuerySorting(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionId().asc();
  verifyQueryResults(query,3);
  query=repositoryService.createProcessDefinitionQuery().orderByDeploymentId().asc();
  verifyQueryResults(query,3);
  query=repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionKey().asc();
  verifyQueryResults(query,3);
  query=repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().asc();
  verifyQueryResults(query,3);
  query=repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionId().desc();
  verifyQueryResults(query,3);
  query=repositoryService.createProcessDefinitionQuery().orderByDeploymentId().desc();
  verifyQueryResults(query,3);
  query=repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionKey().desc();
  verifyQueryResults(query,3);
  query=repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().desc();
  verifyQueryResults(query,3);
  query=repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionKey().asc().orderByProcessDefinitionVersion().desc();
  List<ProcessDefinition> processDefinitions=query.list();
  assertEquals(3,processDefinitions.size());
  assertEquals("one",processDefinitions.get(0).getKey());
  assertEquals(2,processDefinitions.get(0).getVersion());
  assertEquals("one",processDefinitions.get(1).getKey());
  assertEquals(1,processDefinitions.get(1).getVersion());
  assertEquals("two",processDefinitions.get(2).getKey());
  assertEquals(1,processDefinitions.get(2).getVersion());
}
