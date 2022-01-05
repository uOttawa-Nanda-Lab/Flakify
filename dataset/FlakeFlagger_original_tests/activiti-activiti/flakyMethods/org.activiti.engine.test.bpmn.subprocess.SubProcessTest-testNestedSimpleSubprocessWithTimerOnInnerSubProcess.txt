@Deployment public void testNestedSimpleSubprocessWithTimerOnInnerSubProcess(){
  Date startTime=new Date();
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("nestedSubProcessWithTimer");
  Task subProcessTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Task in subprocess",subProcessTask.getName());
  processEngineConfiguration.getClock().setCurrentTime(new Date(startTime.getTime() + (60 * 60 * 1000) + 1000));
  waitForJobExecutorToProcessAllJobs(5000L,50L);
  Task escalationTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Escalated task",escalationTask.getName());
  taskService.complete(escalationTask.getId());
  Task taskAfterSubProcess=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Task after subprocesses",taskAfterSubProcess.getName());
}
