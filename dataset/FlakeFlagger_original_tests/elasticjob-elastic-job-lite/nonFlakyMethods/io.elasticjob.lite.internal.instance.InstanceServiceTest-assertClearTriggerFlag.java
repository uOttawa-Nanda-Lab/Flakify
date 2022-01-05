@Test public void assertClearTriggerFlag(){
  instanceService.clearTriggerFlag();
  jobNodeStorage.updateJobNode("instances/127.0.0.1@-@0","");
}
