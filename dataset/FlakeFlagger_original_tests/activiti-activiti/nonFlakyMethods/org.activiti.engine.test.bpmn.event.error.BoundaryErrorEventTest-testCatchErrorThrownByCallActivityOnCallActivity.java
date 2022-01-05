@Deployment(resources={"org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.testCatchErrorThrownByCallActivityOnCallActivity.bpmn20.xml","org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.subprocess2ndLevel.bpmn20.xml","org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.subprocess.bpmn20.xml"}) public void testCatchErrorThrownByCallActivityOnCallActivity() throws InterruptedException {
  String procId=runtimeService.startProcessInstanceByKey("catchErrorOnCallActivity2ndLevel").getId();
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("Task in subprocess",task.getName());
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().singleResult();
  assertEquals("Escalated Task",task.getName());
  taskService.complete(task.getId());
  assertProcessEnded(procId);
}
