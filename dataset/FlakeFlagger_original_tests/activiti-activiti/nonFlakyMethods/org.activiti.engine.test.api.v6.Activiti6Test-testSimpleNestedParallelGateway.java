@Test @org.activiti.engine.test.Deployment public void testSimpleNestedParallelGateway(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("simpleParallelGateway");
  assertNotNull(processInstance);
  assertFalse(processInstance.isEnded());
  List<Task> tasks=taskService.createTaskQuery().processDefinitionKey("simpleParallelGateway").orderByTaskName().asc().list();
  assertEquals(4,tasks.size());
  assertEquals("Task a",tasks.get(0).getName());
  assertEquals("Task b1",tasks.get(1).getName());
  assertEquals("Task b2",tasks.get(2).getName());
  assertEquals("Task c",tasks.get(3).getName());
  for (  Task task : tasks) {
    taskService.complete(task.getId());
  }
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
}
