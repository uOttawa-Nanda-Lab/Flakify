@Deployment(resources={"org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.testCatchErrorOnJavaScriptScriptTask.bpmn20.xml"}) public void testCatchErrorOnJavaScriptScriptTask(){
  if (JvmUtil.isAtLeastJDK7()) {
    String procId=runtimeService.startProcessInstanceByKey("catchErrorOnScriptTask").getId();
    assertProcessEnded(procId);
  }
}
