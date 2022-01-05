@Deployment public void testMultiInstanceParalelReceiveTaskWithTimer(){
  Date startTime=new Date();
  processEngineConfiguration.getClock().setCurrentTime(startTime);
  runtimeService.startProcessInstanceByKey("multiInstanceReceiveWithTimer");
  List<Execution> executions=runtimeService.createExecutionQuery().activityId("theReceiveTask").list();
  assertEquals(3,executions.size());
  runtimeService.trigger(executions.get(1).getId());
  processEngineConfiguration.getClock().setCurrentTime(new Date(startTime.getTime() + 60000L));
  waitForJobExecutorToProcessAllJobs(10000L,1000L);
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("Task after timer",task.getName());
  taskService.complete(task.getId());
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
