/** 
 * Test to verify listeners on a process-definition are only called for events related to that definition.
 */
@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml","org/activiti/engine/test/api/event/simpleProcess.bpmn20.xml"}) public void testProcessDefinitionScopedListener() throws Exception {
  ProcessDefinition firstDefinition=repositoryService.createProcessDefinitionQuery().deploymentId(deploymentIdFromDeploymentAnnotation).processDefinitionKey("oneTaskProcess").singleResult();
  assertNotNull(firstDefinition);
  ProcessDefinition secondDefinition=repositoryService.createProcessDefinitionQuery().deploymentId(deploymentIdFromDeploymentAnnotation).processDefinitionKey("simpleProcess").singleResult();
  assertNotNull(firstDefinition);
  TestActivitiEventListener listener=new TestActivitiEventListener();
  BpmnModel bpmnModel=repositoryService.getBpmnModel(firstDefinition.getId());
  assertNotNull(bpmnModel);
  ((ActivitiEventSupport)bpmnModel.getEventSupport()).addEventListener(listener);
  ProcessInstance processInstance=runtimeService.startProcessInstanceById(firstDefinition.getId());
  assertNotNull(processInstance);
  assertFalse(listener.getEventsReceived().isEmpty());
  listener.clearEventsReceived();
  ProcessInstance otherInstance=runtimeService.startProcessInstanceById(secondDefinition.getId());
  assertNotNull(otherInstance);
  assertTrue(listener.getEventsReceived().isEmpty());
}
