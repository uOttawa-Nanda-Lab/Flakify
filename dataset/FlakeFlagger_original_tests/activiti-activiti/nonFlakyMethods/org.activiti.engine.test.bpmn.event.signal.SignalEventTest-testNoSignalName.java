public void testNoSignalName(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/signal/SignalEventTests.noSignalName.bpmn20.xml").deploy();
    fail("exception expected");
  }
 catch (  Exception e) {
    if (!e.getMessage().contains(Problems.SIGNAL_MISSING_NAME)) {
      fail("different exception expected, was " + e.getMessage());
    }
  }
}
