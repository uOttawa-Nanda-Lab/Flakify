@org.activiti.engine.test.Deployment public void testSetProcessDefinitionCategory(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  assertEquals("testCategory",processDefinition.getCategory());
  processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionCategory("testCategory").singleResult();
  assertNotNull(processDefinition);
  long count=runtimeService.createProcessInstanceQuery().count();
  runtimeService.startProcessInstanceById(processDefinition.getId());
  long newCount=runtimeService.createProcessInstanceQuery().count();
  assertTrue(newCount == count + 1);
  repositoryService.setProcessDefinitionCategory(processDefinition.getId(),"UpdatedCategory");
  assertEquals(0,repositoryService.createProcessDefinitionQuery().processDefinitionCategory("testCategory").count());
  processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionCategory("UpdatedCategory").singleResult();
  assertNotNull(processDefinition);
  runtimeService.startProcessInstanceById(processDefinition.getId());
  newCount=runtimeService.createProcessInstanceQuery().count();
  assertTrue(newCount == count + 2);
  repositoryService.setProcessDefinitionCategory(processDefinition.getId(),null);
  assertEquals(0,repositoryService.createProcessDefinitionQuery().processDefinitionCategory("testCategory").count());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().processDefinitionCategory("UpdatedCategory").count());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().processDefinitionCategoryNotEquals("UpdatedCategory").count());
}
