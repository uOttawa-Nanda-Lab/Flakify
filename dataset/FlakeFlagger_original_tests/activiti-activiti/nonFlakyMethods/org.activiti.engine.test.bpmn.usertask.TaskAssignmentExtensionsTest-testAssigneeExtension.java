@Deployment public void testAssigneeExtension(){
  runtimeService.startProcessInstanceByKey("assigneeExtension");
  List<Task> tasks=taskService.createTaskQuery().taskAssignee(KERMIT).list();
  assertEquals(1,tasks.size());
  assertEquals("my task",tasks.get(0).getName());
}
