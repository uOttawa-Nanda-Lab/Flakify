public void testMultipleCompensationCatchEventsFails(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/compensate/CompensateEventTest.testMultipleCompensationCatchEventsFails.bpmn20.xml").deploy();
    fail("exception expected");
  }
 catch (  Exception e) {
  }
}
