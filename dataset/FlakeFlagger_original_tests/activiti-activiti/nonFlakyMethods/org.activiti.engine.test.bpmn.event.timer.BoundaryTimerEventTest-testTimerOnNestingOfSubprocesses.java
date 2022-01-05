@Deployment public void testTimerOnNestingOfSubprocesses(){
  Date testStartTime=processEngineConfiguration.getClock().getCurrentTime();
  runtimeService.startProcessInstanceByKey("timerOnNestedSubprocesses");
  List<Task> tasks=taskService.createTaskQuery().orderByTaskName().asc().list();
  assertEquals(2,tasks.size());
  assertEquals("Inner subprocess task 1",tasks.get(0).getName());
  assertEquals("Inner subprocess task 2",tasks.get(1).getName());
  processEngineConfiguration.getClock().setCurrentTime(new Date(testStartTime.getTime() + ((2 * 60 * 60* 1000) + 5000)));
  Job timer=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(timer.getId());
  managementService.executeJob(timer.getId());
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("task outside subprocess",task.getName());
}
