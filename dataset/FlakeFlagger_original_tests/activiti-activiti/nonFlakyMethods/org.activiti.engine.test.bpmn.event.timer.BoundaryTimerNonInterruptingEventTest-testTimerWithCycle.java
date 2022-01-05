@Deployment public void testTimerWithCycle() throws Exception {
  String processInstanceId=runtimeService.startProcessInstanceByKey("nonInterruptingCycle").getId();
  List<Job> jobs=managementService.createTimerJobQuery().processInstanceId(processInstanceId).list();
  assertEquals(1,jobs.size());
  waitForJobExecutorToProcessAllJobs(2000,100);
  assertEquals(1,managementService.createTimerJobQuery().processInstanceId(processInstanceId).count());
  moveByMinutes(60);
  waitForJobExecutorToProcessAllJobs(2000,100);
  assertEquals(1,managementService.createTimerJobQuery().processInstanceId(processInstanceId).count());
  Task task=taskService.createTaskQuery().taskDefinitionKey("task").singleResult();
  taskService.complete(task.getId());
  moveByMinutes(60);
  try {
    waitForJobExecutorToProcessAllJobs(2000,100);
  }
 catch (  Exception ex) {
    fail("No more jobs since the user completed the task");
  }
}
