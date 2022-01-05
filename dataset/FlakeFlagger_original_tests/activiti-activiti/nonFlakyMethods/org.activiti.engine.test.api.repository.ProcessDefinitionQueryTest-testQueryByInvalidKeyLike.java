public void testQueryByInvalidKeyLike(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionKeyLike("%invalid%");
  verifyQueryResults(query,0);
  try {
    repositoryService.createProcessDefinitionQuery().processDefinitionKeyLike(null);
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
