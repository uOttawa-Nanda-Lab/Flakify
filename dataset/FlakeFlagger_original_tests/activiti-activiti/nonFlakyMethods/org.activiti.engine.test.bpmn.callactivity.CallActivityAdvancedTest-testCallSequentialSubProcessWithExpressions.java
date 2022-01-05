@Deployment(resources={"org/activiti/engine/test/bpmn/callactivity/CallActivity.testCallSequentialSubProcess.bpmn20.xml","org/activiti/engine/test/bpmn/callactivity/CallActivity.testCallSimpleSubProcessWithExpressions.bpmn20.xml","org/activiti/engine/test/bpmn/callactivity/simpleSubProcess.bpmn20.xml","org/activiti/engine/test/bpmn/callactivity/simpleSubProcess2.bpmn20.xml"}) public void testCallSequentialSubProcessWithExpressions(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("callSequentialSubProcess");
  TaskQuery taskQuery=taskService.createTaskQuery();
  Task taskBeforeSubProcess=taskQuery.singleResult();
  assertEquals("Task before subprocess",taskBeforeSubProcess.getName());
  taskService.setVariable(taskBeforeSubProcess.getId(),"simpleSubProcessExpression","simpleSubProcess");
  taskService.complete(taskBeforeSubProcess.getId());
  Task taskInSubProcess=taskQuery.singleResult();
  assertEquals("Task in subprocess",taskInSubProcess.getName());
  taskService.complete(taskInSubProcess.getId());
  Task taskAfterSubProcess=taskQuery.singleResult();
  assertEquals("Task after subprocess",taskAfterSubProcess.getName());
  taskService.complete(taskAfterSubProcess.getId());
  taskQuery=taskService.createTaskQuery();
  taskBeforeSubProcess=taskQuery.singleResult();
  assertEquals("Task before subprocess",taskBeforeSubProcess.getName());
  taskService.setVariable(taskBeforeSubProcess.getId(),"simpleSubProcessExpression","simpleSubProcess2");
  taskService.complete(taskBeforeSubProcess.getId());
  taskInSubProcess=taskQuery.singleResult();
  assertEquals("Task in subprocess 2",taskInSubProcess.getName());
  taskService.complete(taskInSubProcess.getId());
  taskAfterSubProcess=taskQuery.singleResult();
  assertEquals("Task after subprocess",taskAfterSubProcess.getName());
  taskService.complete(taskAfterSubProcess.getId());
  assertProcessEnded(processInstance.getId());
}
