@Deployment(resources="org/activiti/engine/test/api/task/taskDefinitionProcess.bpmn20.xml") public void testTaskDefinitionKeyLike() throws Exception {
  runtimeService.startProcessInstanceByKey("taskDefinitionKeyProcess");
  List<Task> tasks=taskService.createTaskQuery().taskDefinitionKeyLike("taskKey1%").orderByTaskName().asc().list();
  assertNotNull(tasks);
  assertEquals(2,tasks.size());
  assertEquals("taskKey1",tasks.get(0).getTaskDefinitionKey());
  assertEquals("taskKey123",tasks.get(1).getTaskDefinitionKey());
  tasks=taskService.createTaskQuery().taskDefinitionKeyLike("%123").orderByTaskName().asc().list();
  assertNotNull(tasks);
  assertEquals(1,tasks.size());
  assertEquals("taskKey123",tasks.get(0).getTaskDefinitionKey());
  tasks=taskService.createTaskQuery().taskDefinitionKeyLike("%Key12%").orderByTaskName().asc().list();
  assertNotNull(tasks);
  assertEquals(1,tasks.size());
  assertEquals("taskKey123",tasks.get(0).getTaskDefinitionKey());
  Long count=taskService.createTaskQuery().taskDefinitionKeyLike("%unexistingKey%").count();
  assertEquals(0L,count.longValue());
}
