public void testQueryByInvalidName(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionName("invalid");
  verifyQueryResults(query,0);
  try {
    repositoryService.createProcessDefinitionQuery().processDefinitionName(null);
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
