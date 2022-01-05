/** 
 * Test to verify if event listeners defined in the BPMN XML which have illegal event-types cause an exception on deploy.
 */
public void testProcessDefinitionListenerDefinitionIllegalType() throws Exception {
  org.activiti.engine.repository.Deployment deployment=null;
  try {
    deployment=repositoryService.createDeployment().addClasspathResource("org/activiti/standalone/event/invalidEventListenerType.bpmn20.xml").deploy();
    fail("Exception expected");
  }
 catch (  ActivitiException ae) {
    assertTrue(ae instanceof ActivitiIllegalArgumentException);
    assertEquals("Invalid event-type: invalid",ae.getMessage());
  }
 finally {
    if (deployment != null) {
      repositoryService.deleteDeployment(deployment.getId(),true);
    }
  }
}
