public void testSignalNoRef(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/signal/SignalEventTests.signalNoRef.bpmn20.xml").deploy();
    fail("exception expected");
  }
 catch (  Exception e) {
    if (!e.getMessage().contains(Problems.SIGNAL_EVENT_MISSING_SIGNAL_REF)) {
      fail("different exception expected");
    }
  }
}
