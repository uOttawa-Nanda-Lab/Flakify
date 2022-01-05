public void testProcessDefinitionDescription(){
  String deploymentId=deployProcessString(("<definitions " + NAMESPACE + " "+ TARGET_NAMESPACE+ ">"+ "  <process id='test' name='test'><documentation>This is a test</documentation></process></definitions>"));
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
  assertEquals("This is a test",processDefinition.getDescription());
  deleteDeployments(Collections.singletonList(deploymentId));
}
