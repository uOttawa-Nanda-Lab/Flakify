/** 
 * Validate fix for ACT-1627: Not throwing assignment event on every update
 */
@Deployment(resources={"org/activiti/examples/bpmn/tasklistener/TaskListenerTest.bpmn20.xml"}) public void testTaskAssignmentListenerNotCalledWhenAssigneeNotUpdated(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("taskListenerProcess");
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("TaskCreateListener is listening!",task.getDescription());
  taskService.setAssignee(task.getId(),"kermit");
  task=taskService.createTaskQuery().singleResult();
  assertEquals("TaskAssignmentListener is listening: kermit",task.getDescription());
  task.setDescription("Clear");
  taskService.saveTask(task);
  taskService.setAssignee(task.getId(),"kermit");
  task=taskService.createTaskQuery().singleResult();
  assertEquals("Clear",task.getDescription());
  task.setAssignee("kermit");
  taskService.saveTask(task);
  task=taskService.createTaskQuery().singleResult();
  assertEquals("Clear",task.getDescription());
  task.setName("test");
  taskService.saveTask(task);
  task=taskService.createTaskQuery().singleResult();
  assertEquals("Clear",task.getDescription());
  task.setAssignee("john");
  taskService.saveTask(task);
  task=taskService.createTaskQuery().singleResult();
  assertEquals("TaskAssignmentListener is listening: john",task.getDescription());
  runtimeService.deleteProcessInstance(processInstance.getProcessInstanceId(),"");
}
