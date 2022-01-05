@Deployment public void testMultipleCandidateGroups(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("multipleCandidatesGroup");
  List<Task> tasks=taskService.createTaskQuery().taskAssignee(KERMIT).list();
  assertTrue(tasks.isEmpty());
  tasks=taskService.createTaskQuery().taskAssignee(GONZO).list();
  assertTrue(tasks.isEmpty());
  assertEquals(1,taskService.createTaskQuery().taskCandidateUser(KERMIT,KERMITSGROUPS).list().size());
  assertEquals(1,taskService.createTaskQuery().taskCandidateUser(GONZO,GONZOSGROUPS).list().size());
  assertEquals(1,taskService.createTaskQuery().taskCandidateGroup("management").count());
  assertEquals(1,taskService.createTaskQuery().taskCandidateGroup("accountancy").count());
  assertEquals(0,taskService.createTaskQuery().taskCandidateGroup("sales").count());
  tasks=taskService.createTaskQuery().taskCandidateUser(GONZO,GONZOSGROUPS).list();
  Task task=tasks.get(0);
  assertEquals("Approve expenses",task.getName());
  taskService.claim(task.getId(),GONZO);
  assertTrue(taskService.createTaskQuery().taskCandidateUser(KERMIT,KERMITSGROUPS).list().isEmpty());
  assertTrue(taskService.createTaskQuery().taskCandidateUser(GONZO,GONZOSGROUPS).list().isEmpty());
  assertEquals(0,taskService.createTaskQuery().taskCandidateGroup("management").count());
  assertEquals(1,taskService.createTaskQuery().taskAssignee(GONZO).count());
  assertEquals(0,taskService.createTaskQuery().taskAssignee(KERMIT).count());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
}
