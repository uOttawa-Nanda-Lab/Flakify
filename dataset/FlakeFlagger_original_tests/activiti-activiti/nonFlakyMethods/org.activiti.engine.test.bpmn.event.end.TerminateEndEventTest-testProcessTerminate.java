@Deployment public void testProcessTerminate() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample");
  long executionEntities=runtimeService.createExecutionQuery().processInstanceId(pi.getId()).count();
  assertEquals(3,executionEntities);
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("preTerminateTask").singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(pi.getId());
  assertHistoricProcessInstanceDetails(pi);
  assertHistoricProcessInstanceDeleteReason(pi,DeleteReason.TERMINATE_END_EVENT);
  assertHistoricTasksDeleteReason(pi,null,"check before termination");
  assertHistoricTasksDeleteReason(pi,DeleteReason.TERMINATE_END_EVENT,"check before end");
  assertHistoricActivitiesDeleteReason(pi,DeleteReason.TERMINATE_END_EVENT,"preNormalTerminateTask");
  assertHistoricActivitiesDeleteReason(pi,null,"preTerminateTask");
}
