@Deployment(resources={"org/activiti/examples/bpmn/tasklistener/TaskListenerTest.bpmn20.xml"}) public void testTaskCompleteListener(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("taskListenerProcess");
  assertEquals(null,runtimeService.getVariable(processInstance.getId(),"greeting"));
  assertEquals(null,runtimeService.getVariable(processInstance.getId(),"expressionValue"));
  Task task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  assertEquals("Hello from The Process",runtimeService.getVariable(processInstance.getId(),"greeting"));
  assertEquals("Act",runtimeService.getVariable(processInstance.getId(),"shortName"));
}
