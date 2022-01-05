@Deployment(resources={"org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.testUncaughtErrorOnScriptTask.bpmn20.xml"}) public void testUncaughtErrorOnScriptTask(){
  try {
    String procId=runtimeService.startProcessInstanceByKey("uncaughtErrorOnScriptTask").getId();
    fail("The script throws error event with errorCode 'errorUncaught', but no catching boundary event was defined. An exception is expected which did not occur");
    assertProcessEnded(procId);
  }
 catch (  BpmnError e) {
    assertTextPresent("No catching boundary event found for error with errorCode 'errorUncaught', neither in same process nor in parent process",e.getMessage());
  }
}
