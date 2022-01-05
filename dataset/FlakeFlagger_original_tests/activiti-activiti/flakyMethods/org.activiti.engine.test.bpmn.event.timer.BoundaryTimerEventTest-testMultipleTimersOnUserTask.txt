@Deployment public void testMultipleTimersOnUserTask(){
  Date startTime=new Date();
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("multipleTimersOnUserTask");
  TimerJobQuery jobQuery=managementService.createTimerJobQuery().processInstanceId(pi.getId());
  List<Job> jobs=jobQuery.list();
  assertEquals(3,jobs.size());
  processEngineConfiguration.getClock().setCurrentTime(new Date(startTime.getTime() + ((60 * 60 * 1000) + 5000)));
  waitForJobExecutorToProcessAllJobs(5000L,25L);
  assertEquals(0L,jobQuery.count());
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("Third Task",task.getName());
}
