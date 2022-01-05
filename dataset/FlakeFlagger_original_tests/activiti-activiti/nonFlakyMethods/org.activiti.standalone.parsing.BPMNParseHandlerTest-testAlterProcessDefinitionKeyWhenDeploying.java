@Deployment public void testAlterProcessDefinitionKeyWhenDeploying() throws Exception {
  assertEquals(0,repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess").count());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionKey("oneTaskProcess-modified").count());
}
