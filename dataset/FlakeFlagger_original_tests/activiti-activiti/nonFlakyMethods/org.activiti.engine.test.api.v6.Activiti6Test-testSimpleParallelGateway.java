@Test @org.activiti.engine.test.Deployment public void testSimpleParallelGateway(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("simpleParallelGateway");
  assertNotNull(processInstance);
  assertFalse(processInstance.isEnded());
  List<Task> tasks=taskService.createTaskQuery().processDefinitionKey("simpleParallelGateway").orderByTaskName().asc().list();
  assertEquals(2,tasks.size());
  assertEquals("Task a",tasks.get(0).getName());
  assertEquals("Task b",tasks.get(1).getName());
  for (  Task task : tasks) {
    taskService.complete(task.getId());
  }
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
}
