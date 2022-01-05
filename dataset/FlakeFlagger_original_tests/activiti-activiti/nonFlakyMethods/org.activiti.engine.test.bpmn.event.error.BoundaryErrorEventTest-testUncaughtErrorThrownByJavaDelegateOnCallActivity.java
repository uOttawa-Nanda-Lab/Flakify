@Deployment(resources={"org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.testUncaughtErrorThrownByJavaDelegateOnCallActivity-parent.bpmn20.xml","org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.testCatchErrorThrownByJavaDelegateOnCallActivity-child.bpmn20.xml"}) public void testUncaughtErrorThrownByJavaDelegateOnCallActivity(){
  try {
    runtimeService.startProcessInstanceByKey("uncaughtErrorThrownByJavaDelegateOnCallActivity-parent");
  }
 catch (  BpmnError e) {
    assertTextPresent("No catching boundary event found for error with errorCode '23', neither in same process nor in parent process",e.getMessage());
  }
}
