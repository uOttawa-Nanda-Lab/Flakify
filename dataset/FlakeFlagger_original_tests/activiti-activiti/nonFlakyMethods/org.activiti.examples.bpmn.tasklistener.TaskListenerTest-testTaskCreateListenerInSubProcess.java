@Deployment(resources={"org/activiti/examples/bpmn/tasklistener/TaskListenerInSubProcessTest.bpmn20.xml"}) public void testTaskCreateListenerInSubProcess(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("taskListenerInSubProcess");
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("Schedule meeting",task.getName());
  assertEquals("TaskCreateListener is listening!",task.getDescription());
  runtimeService.deleteProcessInstance(processInstance.getProcessInstanceId(),"");
}
