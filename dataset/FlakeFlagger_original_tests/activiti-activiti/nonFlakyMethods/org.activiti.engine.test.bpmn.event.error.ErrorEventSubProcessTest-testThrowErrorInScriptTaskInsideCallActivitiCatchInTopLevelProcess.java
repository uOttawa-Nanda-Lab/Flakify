@Deployment(resources={"org/activiti/engine/test/bpmn/event/error/ErrorEventSubProcessTest.testThrowErrorInScriptTaskInsideCallActivitiCatchInTopLevelProcess.bpmn20.xml","org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.testCatchErrorThrownByJavaDelegateOnCallActivity-child.bpmn20.xml"}) public void testThrowErrorInScriptTaskInsideCallActivitiCatchInTopLevelProcess(){
  String procId=runtimeService.startProcessInstanceByKey("testThrowErrorInScriptTaskInsideCallActivitiCatchInTopLevelProcess").getId();
  assertThatErrorHasBeenCaught(procId);
}
