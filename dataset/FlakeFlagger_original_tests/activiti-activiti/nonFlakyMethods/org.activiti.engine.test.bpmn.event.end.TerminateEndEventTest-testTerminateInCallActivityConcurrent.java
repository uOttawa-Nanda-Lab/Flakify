@Deployment(resources={"org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.testTerminateInCallActivityConcurrent.bpmn","org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.subProcessConcurrentTerminate.bpmn"}) public void testTerminateInCallActivityConcurrent() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample");
  long executionEntities=runtimeService.createExecutionQuery().count();
  assertTrue(executionEntities > 0);
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("preNormalEnd").singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(pi.getId());
  assertHistoricProcessInstanceDetails(pi);
}
