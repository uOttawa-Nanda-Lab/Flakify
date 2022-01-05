@Deployment(resources="org/activiti/engine/test/api/task/taskDefinitionProcess.bpmn20.xml") public void testTaskDefinitionKeyLikeOr() throws Exception {
  runtimeService.startProcessInstanceByKey("taskDefinitionKeyProcess");
  List<Task> tasks=taskService.createTaskQuery().or().taskId("invalid").taskDefinitionKeyLike("taskKey1%").orderByTaskName().asc().list();
  assertNotNull(tasks);
  assertEquals(2,tasks.size());
  assertEquals("taskKey1",tasks.get(0).getTaskDefinitionKey());
  assertEquals("taskKey123",tasks.get(1).getTaskDefinitionKey());
  tasks=taskService.createTaskQuery().or().taskDefinitionKeyLike("%123").taskId("invalid").orderByTaskName().asc().list();
  assertNotNull(tasks);
  assertEquals(1,tasks.size());
  assertEquals("taskKey123",tasks.get(0).getTaskDefinitionKey());
  tasks=taskService.createTaskQuery().or().taskDefinitionKeyLike("%Key12%").taskId("invalid").orderByTaskName().asc().list();
  assertNotNull(tasks);
  assertEquals(1,tasks.size());
  assertEquals("taskKey123",tasks.get(0).getTaskDefinitionKey());
  Long count=taskService.createTaskQuery().or().taskId("invalid").taskDefinitionKeyLike("%unexistingKey%").count();
  assertEquals(0L,count.longValue());
}
