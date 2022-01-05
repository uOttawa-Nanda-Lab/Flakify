@Deployment(resources={"org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.testCatchErrorThrownByJavaDelegateOnCallActivity-parent.bpmn20.xml","org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.testCatchErrorThrownByJavaDelegateOnCallActivity-child.bpmn20.xml"}) public void testCatchErrorThrownByJavaDelegateOnCallActivity(){
  String procId=runtimeService.startProcessInstanceByKey("catchErrorThrownByJavaDelegateOnCallActivity-parent").getId();
  assertThatErrorHasBeenCaught(procId);
}
