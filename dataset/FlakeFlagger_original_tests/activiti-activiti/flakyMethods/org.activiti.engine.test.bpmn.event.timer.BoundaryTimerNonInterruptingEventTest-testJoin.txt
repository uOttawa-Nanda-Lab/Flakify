@Deployment public void testJoin(){
  Date startTime=new Date();
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("testJoin");
  Task task1=taskService.createTaskQuery().singleResult();
  assertEquals("Main Task",task1.getName());
  TimerJobQuery jobQuery=managementService.createTimerJobQuery().processInstanceId(pi.getId());
  List<Job> jobs=jobQuery.list();
  assertEquals(1,jobs.size());
  processEngineConfiguration.getClock().setCurrentTime(new Date(startTime.getTime() + ((60 * 60 * 1000) + 5000)));
  waitForJobExecutorToProcessAllJobs(5000L,25L);
  assertEquals(0L,jobQuery.count());
  assertEquals(2L,taskService.createTaskQuery().count());
  taskService.complete(task1.getId());
  assertEquals(1L,taskService.createTaskQuery().count());
  Task task2=taskService.createTaskQuery().singleResult();
  assertEquals("Escalation Task",task2.getName());
  taskService.complete(task2.getId());
  assertProcessEnded(pi.getId());
}
