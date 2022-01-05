public void testQueryByMessageSubscription(){
  Deployment deployment=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/repository/processWithNewBookingMessage.bpmn20.xml").addClasspathResource("org/activiti/engine/test/api/repository/processWithNewInvoiceMessage.bpmn20.xml").deploy();
  assertEquals(1,repositoryService.createProcessDefinitionQuery().messageEventSubscriptionName("newInvoiceMessage").count());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().messageEventSubscriptionName("newBookingMessage").count());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().messageEventSubscriptionName("bogus").count());
  repositoryService.deleteDeployment(deployment.getId());
}
