@Deployment public void testCandidateGroups(){
  runtimeService.startProcessInstanceByKey("taskCandidateExample");
  List<Task> tasks=taskService.createTaskQuery().taskCandidateGroup("management").list();
  assertEquals(1,tasks.size());
  assertEquals("theTask",tasks.get(0).getTaskDefinitionKey());
  taskService.complete(tasks.get(0).getId());
  tasks=taskService.createTaskQuery().taskCandidateGroup("accounting").list();
  assertEquals(1,tasks.size());
  assertEquals("theOtherTask",tasks.get(0).getTaskDefinitionKey());
  taskService.complete(tasks.get(0).getId());
}
