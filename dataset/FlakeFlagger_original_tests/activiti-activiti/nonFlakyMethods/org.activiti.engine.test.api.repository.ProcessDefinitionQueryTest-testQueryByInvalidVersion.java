public void testQueryByInvalidVersion(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionVersion(3);
  verifyQueryResults(query,0);
  try {
    repositoryService.createProcessDefinitionQuery().processDefinitionVersion(-1).list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
  try {
    repositoryService.createProcessDefinitionQuery().processDefinitionVersion(null).list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
