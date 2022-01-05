public void testQueryByInvalidDeploymentId(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().deploymentId("invalid");
  verifyQueryResults(query,0);
  try {
    repositoryService.createProcessDefinitionQuery().deploymentId(null);
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
