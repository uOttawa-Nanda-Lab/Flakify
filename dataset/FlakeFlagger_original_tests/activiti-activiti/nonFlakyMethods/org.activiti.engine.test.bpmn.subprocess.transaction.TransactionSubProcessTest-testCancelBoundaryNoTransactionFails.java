public void testCancelBoundaryNoTransactionFails(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/subprocess/transaction/TransactionSubProcessTest.testCancelBoundaryNoTransactionFails.bpmn20.xml").deploy();
    fail("exception expected");
  }
 catch (  Exception e) {
    if (!e.getMessage().contains("boundary event with cancelEventDefinition only supported on transaction subprocesses")) {
      fail("different exception expected");
    }
  }
}
