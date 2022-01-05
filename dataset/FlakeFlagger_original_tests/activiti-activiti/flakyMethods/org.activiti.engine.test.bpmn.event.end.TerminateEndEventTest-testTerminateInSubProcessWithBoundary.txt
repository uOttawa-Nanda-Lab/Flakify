@Deployment public void testTerminateInSubProcessWithBoundary() throws Exception {
  Date startTime=new Date();
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventWithBoundary");
  assertEquals(3,taskService.createTaskQuery().processInstanceId(pi.getId()).count());
  processEngineConfiguration.getClock().setCurrentTime(new Date(startTime.getTime() + ((60 * 60 * 1000) + 5000)));
  waitForJobExecutorToProcessAllJobs(5000L,25L);
  assertEquals(0L,managementService.createJobQuery().count());
  assertProcessEnded(pi.getId());
  assertHistoricProcessInstanceDeleteReason(pi,DeleteReason.TERMINATE_END_EVENT);
  assertHistoricTasksDeleteReason(pi,DeleteReason.TERMINATE_END_EVENT,"check before normal end");
  assertHistoricActivitiesDeleteReason(pi,DeleteReason.TERMINATE_END_EVENT,"outerTask");
  pi=runtimeService.startProcessInstanceByKey("terminateEndEventWithBoundary");
  assertEquals(3,taskService.createTaskQuery().processInstanceId(pi.getId()).count());
  assertEquals(1L,managementService.createTimerJobQuery().count());
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("preTermInnerTask").singleResult();
  taskService.complete(task.getId());
  assertEquals(1,taskService.createTaskQuery().processInstanceId(pi.getId()).count());
  assertEquals(0L,managementService.createTimerJobQuery().count());
  task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("outerTask").singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(pi.getId());
  assertHistoricProcessInstanceDetails(pi);
}
