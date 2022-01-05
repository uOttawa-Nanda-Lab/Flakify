public void testDuplicateSignalNames(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/signal/SignalEventTests.duplicateSignalNames.bpmn20.xml").deploy();
    fail("exception expected");
  }
 catch (  Exception e) {
    if (!e.getMessage().contains(Problems.SIGNAL_DUPLICATE_NAME)) {
      fail("different exception expected");
    }
  }
}
