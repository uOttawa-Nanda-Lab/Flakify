public void testQueryByCategoryNotEquals(){
  Deployment deployment=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/repository/processCategoryOne.bpmn20.xml").addClasspathResource("org/activiti/engine/test/api/repository/processCategoryTwo.bpmn20.xml").addClasspathResource("org/activiti/engine/test/api/repository/processCategoryThree.bpmn20.xml").deploy();
  HashSet<String> processDefinitionNames=getProcessDefinitionNames(repositoryService.createProcessDefinitionQuery().processDefinitionCategoryNotEquals("one").list());
  HashSet<String> expectedProcessDefinitionNames=new HashSet<String>();
  expectedProcessDefinitionNames.add("processTwo");
  expectedProcessDefinitionNames.add("processThree");
  assertEquals(expectedProcessDefinitionNames,processDefinitionNames);
  processDefinitionNames=getProcessDefinitionNames(repositoryService.createProcessDefinitionQuery().processDefinitionCategoryNotEquals("two").list());
  expectedProcessDefinitionNames=new HashSet<String>();
  expectedProcessDefinitionNames.add("processOne");
  expectedProcessDefinitionNames.add("processThree");
  assertEquals(expectedProcessDefinitionNames,processDefinitionNames);
  repositoryService.deleteDeployment(deployment.getId());
}
