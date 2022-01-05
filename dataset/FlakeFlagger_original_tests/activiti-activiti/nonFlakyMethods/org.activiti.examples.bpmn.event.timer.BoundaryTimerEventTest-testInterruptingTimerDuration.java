@Deployment public void testInterruptingTimerDuration(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("escalationExample");
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("First line support",task.getName());
  Job timer=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(timer.getId());
  managementService.executeJob(timer.getId());
  task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Handle escalated issue",task.getName());
}
