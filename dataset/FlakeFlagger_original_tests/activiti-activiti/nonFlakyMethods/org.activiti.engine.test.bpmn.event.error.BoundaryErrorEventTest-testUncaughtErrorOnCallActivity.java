@Deployment(resources={"org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.testUncaughtErrorOnCallActivity-parent.bpmn20.xml","org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.subprocess.bpmn20.xml"}) public void testUncaughtErrorOnCallActivity(){
  runtimeService.startProcessInstanceByKey("uncaughtErrorOnCallActivity");
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("Task in subprocess",task.getName());
  try {
    taskService.complete(task.getId());
  }
 catch (  BpmnError e) {
    assertTextPresent("No catching boundary event found for error with errorCode 'myError', neither in same process nor in parent process",e.getMessage());
  }
}
