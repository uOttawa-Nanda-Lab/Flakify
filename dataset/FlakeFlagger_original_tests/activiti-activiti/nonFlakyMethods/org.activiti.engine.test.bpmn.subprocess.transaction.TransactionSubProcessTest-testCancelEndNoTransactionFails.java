public void testCancelEndNoTransactionFails(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/subprocess/transaction/TransactionSubProcessTest.testCancelEndNoTransactionFails.bpmn20.xml").deploy();
    fail("exception expected");
  }
 catch (  Exception e) {
    if (!e.getMessage().contains("end event with cancelEventDefinition only supported inside transaction subprocess")) {
      fail("different exception expected");
    }
  }
}
