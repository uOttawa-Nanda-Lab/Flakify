@Deployment public void testProcessDefinitionDescription(){
  String id=repositoryService.createProcessDefinitionQuery().singleResult().getId();
  ProcessDefinition processDefinition=((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(id);
  assertEquals("This is really good process documentation!",processDefinition.getDescription());
}
