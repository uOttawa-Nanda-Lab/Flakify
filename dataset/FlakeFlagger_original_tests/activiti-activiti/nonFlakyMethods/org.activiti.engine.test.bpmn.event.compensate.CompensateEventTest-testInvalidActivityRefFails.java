public void testInvalidActivityRefFails(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/compensate/CompensateEventTest.testInvalidActivityRefFails.bpmn20.xml").deploy();
    fail("exception expected");
  }
 catch (  Exception e) {
    if (!e.getMessage().contains("Invalid attribute value for 'activityRef':")) {
      fail("different exception expected");
    }
  }
}
