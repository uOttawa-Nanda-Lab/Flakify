public void testQueryByProcessDefinitionIds(){
  List<ProcessDefinition> processDefinitions=repositoryService.createProcessDefinitionQuery().list();
  Set<String> ids=new HashSet<String>();
  for (  ProcessDefinition processDefinition : processDefinitions) {
    ids.add(processDefinition.getId());
  }
  List<ProcessDefinition> queryResults=repositoryService.createProcessDefinitionQuery().processDefinitionIds(ids).list();
  assertEquals(queryResults.size(),ids.size());
  for (  ProcessDefinition processDefinition : queryResults) {
    assertTrue(ids.contains(processDefinition.getId()));
  }
}
