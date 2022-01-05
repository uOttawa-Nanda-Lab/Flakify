public void testSignalNoId(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/signal/SignalEventTests.signalNoId.bpmn20.xml").deploy();
    fail("exception expected");
  }
 catch (  Exception e) {
    if (!e.getMessage().contains(Problems.SIGNAL_MISSING_ID)) {
      fail("different exception expected");
    }
  }
}
