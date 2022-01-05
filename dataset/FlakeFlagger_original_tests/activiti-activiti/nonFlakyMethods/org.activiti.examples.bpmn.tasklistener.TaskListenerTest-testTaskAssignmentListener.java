@Deployment(resources={"org/activiti/examples/bpmn/tasklistener/TaskListenerTest.bpmn20.xml"}) public void testTaskAssignmentListener(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("taskListenerProcess");
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("TaskCreateListener is listening!",task.getDescription());
  taskService.setAssignee(task.getId(),"kermit");
  task=taskService.createTaskQuery().singleResult();
  assertEquals("TaskAssignmentListener is listening: kermit",task.getDescription());
  runtimeService.deleteProcessInstance(processInstance.getProcessInstanceId(),"");
}
