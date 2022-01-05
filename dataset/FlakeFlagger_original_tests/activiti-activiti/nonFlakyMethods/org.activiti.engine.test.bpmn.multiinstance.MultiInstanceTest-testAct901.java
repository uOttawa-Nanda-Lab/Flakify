@Deployment public void testAct901(){
  Date startTime=processEngineConfiguration.getClock().getCurrentTime();
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("multiInstanceSubProcess");
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(pi.getId()).orderByTaskName().asc().list();
  processEngineConfiguration.getClock().setCurrentTime(new Date(startTime.getTime() + 61000L));
  List<Job> timers=managementService.createTimerJobQuery().list();
  assertEquals(5,timers.size());
  for (  Job timer : timers) {
    managementService.moveTimerToExecutableJob(timer.getId());
    managementService.executeJob(timer.getId());
  }
  tasks=taskService.createTaskQuery().processInstanceId(pi.getId()).orderByTaskName().asc().list();
  assertEquals(0,tasks.size());
}
