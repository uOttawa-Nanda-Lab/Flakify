@Test @org.activiti.engine.test.Deployment public void testNonInterruptingMoreComplex(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("nonInterruptingTimer");
  assertNotNull(processInstance);
  assertFalse(processInstance.isEnded());
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();
  assertEquals(2,tasks.size());
  assertEquals("A",tasks.get(0).getName());
  assertEquals("B",tasks.get(1).getName());
  List<Job> jobs=managementService.createTimerJobQuery().list();
  assertEquals(2,jobs.size());
  for (  Job job : jobs) {
    managementService.moveTimerToExecutableJob(job.getId());
    managementService.executeJob(job.getId());
  }
  tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();
  assertEquals(5,tasks.size());
  assertEquals("A",tasks.get(0).getName());
  assertEquals("C",tasks.get(1).getName());
  assertEquals("D",tasks.get(2).getName());
  assertEquals("E",tasks.get(3).getName());
  assertEquals("F",tasks.get(4).getName());
  jobs=managementService.createTimerJobQuery().list();
  assertEquals(1,jobs.size());
  managementService.moveTimerToExecutableJob(jobs.get(0).getId());
  managementService.executeJob(jobs.get(0).getId());
  tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();
  assertEquals(6,tasks.size());
  assertEquals("A",tasks.get(0).getName());
  assertEquals("C",tasks.get(1).getName());
  assertEquals("D",tasks.get(2).getName());
  assertEquals("E",tasks.get(3).getName());
  assertEquals("F",tasks.get(4).getName());
  assertEquals("G",tasks.get(5).getName());
  for (  Task task : tasks) {
    taskService.complete(task.getId());
  }
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
