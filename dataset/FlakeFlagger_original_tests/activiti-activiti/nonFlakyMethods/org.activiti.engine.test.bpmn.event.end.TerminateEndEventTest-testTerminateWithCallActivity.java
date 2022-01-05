@Deployment(resources={"org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.testTerminateWithCallActivity.bpmn","org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.subProcessNoTerminate.bpmn"}) public void testTerminateWithCallActivity() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample");
  ProcessInstance subProcessInstance=runtimeService.createProcessInstanceQuery().superProcessInstanceId(pi.getId()).singleResult();
  assertNotNull(subProcessInstance);
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("preTerminateEnd").singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(pi.getId());
  assertHistoricProcessInstanceDetails(pi);
  assertHistoricProcessInstanceDeleteReason(pi,DeleteReason.TERMINATE_END_EVENT);
  assertHistoricTasksDeleteReason(pi,null,"check before termination");
  assertHistoricTasksDeleteReason(subProcessInstance,DeleteReason.TERMINATE_END_EVENT,"Perform Sample");
  assertHistoricActivitiesDeleteReason(pi,null,"preTerminateEnd");
  assertHistoricActivitiesDeleteReason(subProcessInstance,DeleteReason.TERMINATE_END_EVENT,"task");
}
