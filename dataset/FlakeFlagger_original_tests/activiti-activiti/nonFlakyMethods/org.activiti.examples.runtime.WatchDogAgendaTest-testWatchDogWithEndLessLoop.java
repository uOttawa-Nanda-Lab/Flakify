@Deployment(resources="org/activiti/examples/runtime/WatchDogAgendaTest-endlessloop.bpmn20.xml") public void testWatchDogWithEndLessLoop(){
  try {
    this.runtimeService.startProcessInstanceByKey("endlessloop");
    fail("ActivitiException with 'WatchDog limit exceeded.' message expected.");
  }
 catch (  ActivitiException e) {
    if (!"WatchDog limit exceeded.".equals(e.getMessage())) {
      fail("Unexpected exception " + e);
    }
  }
}
