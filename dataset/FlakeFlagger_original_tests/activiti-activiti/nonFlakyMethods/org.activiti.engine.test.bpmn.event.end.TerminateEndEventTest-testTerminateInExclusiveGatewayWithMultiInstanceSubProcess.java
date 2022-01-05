@Deployment public void testTerminateInExclusiveGatewayWithMultiInstanceSubProcess() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample-terminateAfterExclusiveGateway");
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("preTerminateEnd").singleResult();
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("input",1);
  taskService.complete(task.getId(),variables);
  assertProcessEnded(pi.getId());
  assertHistoricProcessInstanceDetails(pi);
  assertHistoricProcessInstanceDeleteReason(pi,DeleteReason.TERMINATE_END_EVENT);
  assertHistoricTasksDeleteReason(pi,null,"check before termination");
  assertHistoricTasksDeleteReason(pi,DeleteReason.TERMINATE_END_EVENT,"User Task");
  assertHistoricActivitiesDeleteReason(pi,null,"preTerminateEnd");
  assertHistoricActivitiesDeleteReason(pi,DeleteReason.TERMINATE_END_EVENT,"task");
}
