@Deployment(resources={"org/activiti/spring/test/expression/callactivity/CallActivityBasedOnSpringBeansExpressionTest.testCallActivityByExpression.bpmn20.xml","org/activiti/spring/test/expression/callactivity/simpleSubProcess.bpmn20.xml"}) public void testCallActivityByExpression() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testCallActivityByExpression");
  TaskQuery taskQuery=taskService.createTaskQuery();
  Task taskBeforeSubProcess=taskQuery.singleResult();
  assertEquals("Task before subprocess",taskBeforeSubProcess.getName());
  taskService.complete(taskBeforeSubProcess.getId());
  Task taskInSubProcess=taskQuery.singleResult();
  assertEquals("Task in subprocess",taskInSubProcess.getName());
  taskService.complete(taskInSubProcess.getId());
  Task taskAfterSubProcess=taskQuery.singleResult();
  assertEquals("Task after subprocess",taskAfterSubProcess.getName());
  taskService.complete(taskAfterSubProcess.getId());
  assertProcessEnded(processInstance.getId());
}
