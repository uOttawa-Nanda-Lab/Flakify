@Test @org.activiti.engine.test.Deployment public void testConditionsWithoutExclusiveGateway(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testConditions",CollectionUtil.singletonMap("input",2));
  assertNotNull(processInstance);
  assertFalse(processInstance.isEnded());
  Task task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  List<Task> tasks=taskService.createTaskQuery().orderByTaskName().asc().list();
  assertEquals(3,tasks.size());
  assertEquals("A",tasks.get(0).getName());
  assertEquals("B",tasks.get(1).getName());
  assertEquals("C",tasks.get(2).getName());
  for (  Task t : tasks) {
    taskService.complete(t.getId());
  }
  processInstance=runtimeService.startProcessInstanceByKey("testConditions",CollectionUtil.singletonMap("input",20));
  task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  tasks=taskService.createTaskQuery().orderByTaskName().asc().list();
  assertEquals(2,tasks.size());
  assertEquals("B",tasks.get(0).getName());
  assertEquals("C",tasks.get(1).getName());
  for (  Task t : tasks) {
    taskService.complete(t.getId());
  }
  processInstance=runtimeService.startProcessInstanceByKey("testConditions",CollectionUtil.singletonMap("input",200));
  task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  tasks=taskService.createTaskQuery().orderByTaskName().asc().list();
  assertEquals(1,tasks.size());
  assertEquals("C",tasks.get(0).getName());
  for (  Task t : tasks) {
    taskService.complete(t.getId());
  }
}
