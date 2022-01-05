public void testRollBackOnDeployment(){
  assertEquals(0,repositoryService.createProcessDefinitionQuery().count());
  try {
    deployBean.deployProcesses();
    fail();
  }
 catch (  XMLException e) {
  }
  assertEquals(0,repositoryService.createProcessDefinitionQuery().count());
}
