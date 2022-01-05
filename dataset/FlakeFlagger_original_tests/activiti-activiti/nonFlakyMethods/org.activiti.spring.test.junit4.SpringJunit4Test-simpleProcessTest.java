@Test @Deployment public void simpleProcessTest(){
  runtimeService.startProcessInstanceByKey("simpleProcess");
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("My Task",task.getName());
  assertNotNull(activitiSpringRule.getRuntimeService());
  taskService.complete(task.getId());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
}
