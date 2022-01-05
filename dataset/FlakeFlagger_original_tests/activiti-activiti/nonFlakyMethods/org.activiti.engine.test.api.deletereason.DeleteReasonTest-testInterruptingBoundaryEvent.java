@Deployment public void testInterruptingBoundaryEvent(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("deleteReasonProcess");
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("A",task.getName());
  taskService.complete(task.getId());
  Job timerJob=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(timerJob.getId());
  managementService.executeJob(timerJob.getId());
  assertHistoricTasksDeleteReason(processInstance,null,"A");
  assertHistoricTasksDeleteReason(processInstance,DeleteReason.BOUNDARY_EVENT_INTERRUPTING,"B","C","D");
  assertHistoricActivitiesDeleteReason(processInstance,null,"A");
  assertHistoricActivitiesDeleteReason(processInstance,DeleteReason.BOUNDARY_EVENT_INTERRUPTING,"B","C","D","theSubprocess");
}
