@Deployment public void testTaskAssignee(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("taskAssigneeExampleProcess");
  List<Task> tasks=taskService.createTaskQuery().taskAssignee("kermit").list();
  assertEquals(1,tasks.size());
  Task myTask=tasks.get(0);
  assertEquals("Schedule meeting",myTask.getName());
  assertEquals("Schedule an engineering meeting for next week with the new hire.",myTask.getDescription());
  taskService.complete(myTask.getId());
  assertProcessEnded(processInstance.getId());
}
