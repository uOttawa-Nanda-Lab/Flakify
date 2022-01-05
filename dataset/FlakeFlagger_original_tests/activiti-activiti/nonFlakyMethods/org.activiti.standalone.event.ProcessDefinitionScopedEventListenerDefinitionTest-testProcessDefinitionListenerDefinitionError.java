/** 
 * Test to verify listeners defined in the BPMN xml with invalid class/delegateExpression values cause an exception when process is started.
 */
public void testProcessDefinitionListenerDefinitionError() throws Exception {
  org.activiti.engine.repository.Deployment deployment=repositoryService.createDeployment().addClasspathResource("org/activiti/standalone/event/invalidEventListenerExpression.bpmn20.xml").deploy();
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testInvalidEventExpression");
  assertNotNull(processInstance);
  repositoryService.deleteDeployment(deployment.getId(),true);
  deployment=repositoryService.createDeployment().addClasspathResource("org/activiti/standalone/event/invalidEventListenerClass.bpmn20.xml").deploy();
  processInstance=runtimeService.startProcessInstanceByKey("testInvalidEventClass");
  repositoryService.deleteDeployment(deployment.getId(),true);
}
