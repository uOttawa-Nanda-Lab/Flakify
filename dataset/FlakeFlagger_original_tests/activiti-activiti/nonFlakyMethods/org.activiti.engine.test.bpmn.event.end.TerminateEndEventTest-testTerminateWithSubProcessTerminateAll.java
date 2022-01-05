@Deployment public void testTerminateWithSubProcessTerminateAll() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample");
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("preNormalEnd").singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(pi.getId());
  assertHistoricProcessInstanceDetails(pi);
  assertHistoricProcessInstanceDeleteReason(pi,DeleteReason.TERMINATE_END_EVENT);
  assertHistoricTasksDeleteReason(pi,null,"check before end");
  assertHistoricTasksDeleteReason(pi,DeleteReason.TERMINATE_END_EVENT,"check before termination");
  assertHistoricActivitiesDeleteReason(pi,DeleteReason.TERMINATE_END_EVENT,"preTerminateEnd");
  assertHistoricActivitiesDeleteReason(pi,null,"preNormalEnd");
}
