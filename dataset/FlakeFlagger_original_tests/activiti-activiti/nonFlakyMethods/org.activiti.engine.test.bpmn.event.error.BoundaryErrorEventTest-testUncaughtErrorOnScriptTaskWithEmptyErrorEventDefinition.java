@Deployment(resources={"org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.testUncaughtErrorOnScriptTaskWithEmptyErrorEventDefinition.bpmn20.xml"}) public void testUncaughtErrorOnScriptTaskWithEmptyErrorEventDefinition(){
  String procId=runtimeService.startProcessInstanceByKey("uncaughtErrorOnScriptTaskWithEmptyErrorEventDefinition").getId();
  assertProcessEnded(procId);
}
