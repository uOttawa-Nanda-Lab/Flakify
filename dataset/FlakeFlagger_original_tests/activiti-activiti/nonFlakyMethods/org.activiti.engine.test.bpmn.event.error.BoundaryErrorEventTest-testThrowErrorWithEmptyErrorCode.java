public void testThrowErrorWithEmptyErrorCode(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.testThrowErrorWithEmptyErrorCode.bpmn20.xml").deploy();
    fail("ActivitiException expected");
  }
 catch (  ActivitiException re) {
  }
}
