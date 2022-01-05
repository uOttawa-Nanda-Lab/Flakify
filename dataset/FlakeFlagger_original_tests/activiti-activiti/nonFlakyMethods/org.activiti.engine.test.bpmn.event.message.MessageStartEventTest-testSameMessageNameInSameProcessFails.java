public void testSameMessageNameInSameProcessFails(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/message/testSameMessageNameInSameProcessFails.bpmn20.xml").deploy();
    fail("exception expected: Cannot have more than one message event subscription with name 'newInvoiceMessage' for scope");
  }
 catch (  ActivitiException e) {
    e.printStackTrace();
  }
}
