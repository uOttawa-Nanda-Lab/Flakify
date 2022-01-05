@Deployment(resources={"org/activiti/examples/bpmn/tasklistener/TaskListenerTest.testTaskListenersOnDelete.bpmn20.xml"}) public void testTaskListenersOnDeleteByComplete(){
  TaskDeleteListener.clear();
  runtimeService.startProcessInstanceByKey("executionListenersOnDelete");
  List<Task> tasks=taskService.createTaskQuery().list();
  assertNotNull(tasks);
  assertEquals(1,tasks.size());
  Task task=taskService.createTaskQuery().taskName("User Task 1").singleResult();
  assertNotNull(task);
  assertEquals(0,TaskDeleteListener.getCurrentMessages().size());
  assertEquals(0,TaskSimpleCompleteListener.getCurrentMessages().size());
  taskService.complete(task.getId());
  tasks=taskService.createTaskQuery().list();
  assertNotNull(tasks);
  assertEquals(0,tasks.size());
  assertEquals(1,TaskDeleteListener.getCurrentMessages().size());
  assertEquals("Delete Task Listener executed.",TaskDeleteListener.getCurrentMessages().get(0));
  assertEquals(1,TaskSimpleCompleteListener.getCurrentMessages().size());
  assertEquals("Complete Task Listener executed.",TaskSimpleCompleteListener.getCurrentMessages().get(0));
}
