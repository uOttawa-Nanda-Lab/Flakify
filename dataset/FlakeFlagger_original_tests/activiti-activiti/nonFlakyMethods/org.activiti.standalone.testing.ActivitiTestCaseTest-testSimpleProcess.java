@Deployment public void testSimpleProcess(){
  runtimeService.startProcessInstanceByKey("simpleProcess");
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("My Task",task.getName());
  taskService.complete(task.getId());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
}
