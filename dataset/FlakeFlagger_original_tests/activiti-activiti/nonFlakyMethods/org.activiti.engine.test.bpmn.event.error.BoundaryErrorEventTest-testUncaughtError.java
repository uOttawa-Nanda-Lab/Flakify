@Deployment(resources={"org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.subprocess.bpmn20.xml"}) public void testUncaughtError(){
  runtimeService.startProcessInstanceByKey("simpleSubProcess");
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("Task in subprocess",task.getName());
  try {
    taskService.complete(task.getId());
    fail("No catching boundary event found for error with errorCode 'myError', neither in same process nor in parent process but no Exception is thrown");
  }
 catch (  BpmnError e) {
    assertTextPresent("No catching boundary event found for error with errorCode 'myError', neither in same process nor in parent process",e.getMessage());
  }
}
