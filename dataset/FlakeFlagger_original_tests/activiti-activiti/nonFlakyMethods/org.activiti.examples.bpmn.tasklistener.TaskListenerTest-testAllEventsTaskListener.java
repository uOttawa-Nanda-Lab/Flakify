@Deployment(resources={"org/activiti/examples/bpmn/tasklistener/TaskListenerTest.bpmn20.xml"}) public void testAllEventsTaskListener(){
  runtimeService.startProcessInstanceByKey("taskListenerProcess");
  Task task=taskService.createTaskQuery().singleResult();
  taskService.setAssignee(task.getId(),"kermit");
  taskService.complete(task.getId());
  String eventsReceived=(String)runtimeService.getVariable(task.getProcessInstanceId(),"events");
  assertEquals("create - assignment - complete - delete",eventsReceived);
}
