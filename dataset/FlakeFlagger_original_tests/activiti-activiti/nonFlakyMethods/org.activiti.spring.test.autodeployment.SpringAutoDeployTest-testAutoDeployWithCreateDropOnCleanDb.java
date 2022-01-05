public void testAutoDeployWithCreateDropOnCleanDb(){
  createAppContext(CTX_CREATE_DROP_CLEAN_DB);
  assertEquals(1,repositoryService.createDeploymentQuery().count());
  assertEquals(3,repositoryService.createProcessDefinitionQuery().count());
}
