@Deployment public void testOwnerExtension(){
  runtimeService.startProcessInstanceByKey("ownerExtension");
  List<Task> tasks=taskService.createTaskQuery().taskOwner(GONZO).list();
  assertEquals(1,tasks.size());
  assertEquals("my task",tasks.get(0).getName());
}
