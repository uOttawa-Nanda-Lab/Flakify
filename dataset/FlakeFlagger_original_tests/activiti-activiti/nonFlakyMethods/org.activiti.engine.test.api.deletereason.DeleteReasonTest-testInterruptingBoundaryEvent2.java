@Deployment public void testInterruptingBoundaryEvent2(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("deleteReasonReceiveTask");
  Execution execution=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).activityId("A").singleResult();
  assertNotNull(execution);
  runtimeService.trigger(execution.getId());
  Job timerJob=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(timerJob.getId());
  managementService.executeJob(timerJob.getId());
  assertHistoricActivitiesDeleteReason(processInstance,null,"A");
  assertHistoricActivitiesDeleteReason(processInstance,DeleteReason.BOUNDARY_EVENT_INTERRUPTING,"B","C","theSubprocess");
}
