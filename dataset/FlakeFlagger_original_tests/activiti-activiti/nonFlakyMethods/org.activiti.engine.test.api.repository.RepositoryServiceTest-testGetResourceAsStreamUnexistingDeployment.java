@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testGetResourceAsStreamUnexistingDeployment(){
  try {
    repositoryService.getResourceAsStream("unexistingdeployment","org/activiti/engine/test/api/unexistingProcess.bpmn.xml");
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("deployment does not exist",ae.getMessage());
    assertEquals(org.activiti.engine.repository.Deployment.class,ae.getObjectClass());
  }
}
