@Deployment(resources={"org/activiti/examples/bpmn/tasklistener/TaskListenerTest.bpmn20.xml"}) public void testTaskListenerWithExpression(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("taskListenerProcess");
  assertEquals(null,runtimeService.getVariable(processInstance.getId(),"greeting2"));
  Task task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  assertEquals("Write meeting notes",runtimeService.getVariable(processInstance.getId(),"greeting2"));
}
