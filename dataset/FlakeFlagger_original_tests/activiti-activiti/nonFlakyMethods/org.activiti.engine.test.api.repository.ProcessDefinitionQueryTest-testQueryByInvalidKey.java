public void testQueryByInvalidKey(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionKey("invalid");
  verifyQueryResults(query,0);
  try {
    repositoryService.createProcessDefinitionQuery().processDefinitionKey(null);
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
