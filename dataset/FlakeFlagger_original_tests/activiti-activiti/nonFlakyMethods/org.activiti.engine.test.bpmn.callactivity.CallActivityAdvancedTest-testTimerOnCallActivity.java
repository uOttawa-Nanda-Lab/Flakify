@Deployment(resources={"org/activiti/engine/test/bpmn/callactivity/CallActivity.testTimerOnCallActivity.bpmn20.xml","org/activiti/engine/test/bpmn/callactivity/simpleSubProcess.bpmn20.xml"}) public void testTimerOnCallActivity(){
  Date startTime=processEngineConfiguration.getClock().getCurrentTime();
  ProcessInstance pi1=runtimeService.startProcessInstanceByKey("timerOnCallActivity");
  TaskQuery taskQuery=taskService.createTaskQuery();
  Task taskInSubProcess=taskQuery.singleResult();
  assertEquals("Task in subprocess",taskInSubProcess.getName());
  ProcessInstance pi2=runtimeService.createProcessInstanceQuery().superProcessInstanceId(pi1.getId()).singleResult();
  processEngineConfiguration.getClock().setCurrentTime(new Date(startTime.getTime() + (6 * 60 * 1000)));
  waitForJobExecutorToProcessAllJobs(10000,5000L);
  Task escalatedTask=taskQuery.singleResult();
  assertEquals("Escalated Task",escalatedTask.getName());
  taskService.complete(escalatedTask.getId());
  assertEquals(0,runtimeService.createExecutionQuery().list().size());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    assertTrue(historyService.createHistoricProcessInstanceQuery().processInstanceId(pi2.getId()).singleResult().getDeleteReason().startsWith(DeleteReason.BOUNDARY_EVENT_INTERRUPTING));
    assertHistoricTasksDeleteReason(pi2,DeleteReason.BOUNDARY_EVENT_INTERRUPTING,"Task in subprocess");
    assertHistoricActivitiesDeleteReason(pi1,DeleteReason.BOUNDARY_EVENT_INTERRUPTING,"callSubProcess");
    assertHistoricActivitiesDeleteReason(pi2,DeleteReason.BOUNDARY_EVENT_INTERRUPTING,"task");
  }
}
