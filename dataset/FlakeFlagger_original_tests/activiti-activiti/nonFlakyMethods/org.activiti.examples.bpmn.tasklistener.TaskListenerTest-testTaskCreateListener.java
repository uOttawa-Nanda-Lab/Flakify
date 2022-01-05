@Deployment(resources={"org/activiti/examples/bpmn/tasklistener/TaskListenerTest.bpmn20.xml"}) public void testTaskCreateListener(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("taskListenerProcess");
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("Schedule meeting",task.getName());
  assertEquals("TaskCreateListener is listening!",task.getDescription());
  runtimeService.deleteProcessInstance(processInstance.getProcessInstanceId(),"");
}
