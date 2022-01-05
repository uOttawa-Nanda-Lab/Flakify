public void testCreateToComplete(){
  Task task=taskService.newTask();
  task.setName("testTask");
  taskService.saveTask(task);
  String taskId=task.getId();
  taskService.addCandidateUser(taskId,"kermit");
  taskService.addCandidateUser(taskId,"gonzo");
  List<Task> tasks=taskService.createTaskQuery().taskCandidateUser("kermit").list();
  assertEquals(1,tasks.size());
  assertEquals("testTask",tasks.get(0).getName());
  tasks=taskService.createTaskQuery().taskCandidateUser("gonzo").list();
  assertEquals(1,tasks.size());
  assertEquals("testTask",tasks.get(0).getName());
  task.setName("Update name");
  taskService.saveTask(task);
  tasks=taskService.createTaskQuery().taskCandidateUser("kermit").list();
  assertEquals(1,tasks.size());
  assertEquals("Update name",tasks.get(0).getName());
  taskService.claim(taskId,"kermit");
  assertTrue(taskService.createTaskQuery().taskCandidateUser("kermit").list().isEmpty());
  assertTrue(taskService.createTaskQuery().taskCandidateUser("gonzo").list().isEmpty());
  taskService.deleteTask(taskId,true);
  assertNull(taskService.createTaskQuery().taskId(taskId).singleResult());
}
