public void testMultipleCancelBoundaryFails(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/subprocess/transaction/TransactionSubProcessTest.testMultipleCancelBoundaryFails.bpmn20.xml").deploy();
    fail("exception expected");
  }
 catch (  Exception e) {
    if (!e.getMessage().contains("multiple boundary events with cancelEventDefinition not supported on same transaction")) {
      fail("different exception expected");
    }
  }
}
