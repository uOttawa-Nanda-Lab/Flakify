@Deployment public void testSingleCandidateGroup(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("singleCandidateGroup");
  List<Task> tasks=taskService.createTaskQuery().taskAssignee(KERMIT).list();
  assertTrue(tasks.isEmpty());
  tasks=taskService.createTaskQuery().taskCandidateUser(KERMIT,KERMITSGROUPS).list();
  assertEquals(1,tasks.size());
  Task task=tasks.get(0);
  assertEquals("Pay out expenses",task.getName());
  taskService.claim(task.getId(),KERMIT);
  tasks=taskService.createTaskQuery().taskCandidateUser(KERMIT,KERMITSGROUPS).list();
  assertTrue(tasks.isEmpty());
  tasks=taskService.createTaskQuery().taskAssignee(KERMIT).list();
  assertEquals(1,tasks.size());
  task=tasks.get(0);
  assertEquals("Pay out expenses",task.getName());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
}
