@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testGetResourceAsStreamUnexistingResourceInExistingDeployment(){
  org.activiti.engine.repository.Deployment deployment=repositoryService.createDeploymentQuery().singleResult();
  try {
    repositoryService.getResourceAsStream(deployment.getId(),"org/activiti/engine/test/api/unexistingProcess.bpmn.xml");
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("no resource found with name",ae.getMessage());
    assertEquals(InputStream.class,ae.getObjectClass());
  }
}
