@Deployment(resources={"org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.testCatchErrorOnGroovyScriptTask.bpmn20.xml"}) public void testCatchErrorOnGroovyScriptTask(){
  String procId=runtimeService.startProcessInstanceByKey("catchErrorOnScriptTask").getId();
  assertProcessEnded(procId);
}
