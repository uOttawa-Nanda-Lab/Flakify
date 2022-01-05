@Deployment(resources={"org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.testCatchErrorThrownByCallActivityOnSubprocess.bpmn20.xml","org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.subprocess.bpmn20.xml"}) public void testCatchErrorThrownByCallActivityOnSubprocess(){
  String procId=runtimeService.startProcessInstanceByKey("catchErrorOnSubprocess").getId();
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("Task in subprocess",task.getName());
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().singleResult();
  assertEquals("Escalated Task",task.getName());
  taskService.complete(task.getId());
  assertProcessEnded(procId);
}
