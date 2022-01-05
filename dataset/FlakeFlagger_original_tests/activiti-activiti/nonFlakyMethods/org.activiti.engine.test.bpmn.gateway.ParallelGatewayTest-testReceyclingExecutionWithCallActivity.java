/** 
 * https://activiti.atlassian.net/browse/ACT-1222
 */
@Deployment public void testReceyclingExecutionWithCallActivity(){
  runtimeService.startProcessInstanceByKey("parent-process");
  TaskQuery query=taskService.createTaskQuery().orderByTaskName().asc();
  List<Task> tasks=query.list();
  assertEquals(2,tasks.size());
  assertEquals("Another task",tasks.get(0).getName());
  assertEquals("Some Task",tasks.get(1).getName());
  taskService.complete(tasks.get(1).getId());
  tasks=query.list();
  assertEquals(1,tasks.size());
  assertEquals("Another task",tasks.get(0).getName());
  taskService.complete(tasks.get(0).getId());
  assertEquals(0,taskService.createTaskQuery().count());
}
