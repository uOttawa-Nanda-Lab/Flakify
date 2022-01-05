@Deployment(resources={"org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.testTerminateInExclusiveGatewayWithCallActivity.bpmn","org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.subProcessNoTerminate.bpmn"}) public void testTerminateInExclusiveGatewayWithCallActivity() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample-terminateAfterExclusiveGateway");
  ProcessInstance subProcessInstance=runtimeService.createProcessInstanceQuery().superProcessInstanceId(pi.getId()).singleResult();
  assertNotNull(subProcessInstance);
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("preTerminateEnd").singleResult();
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("input",1);
  taskService.complete(task.getId(),variables);
  assertProcessEnded(pi.getId());
  assertHistoricProcessInstanceDetails(pi);
}
