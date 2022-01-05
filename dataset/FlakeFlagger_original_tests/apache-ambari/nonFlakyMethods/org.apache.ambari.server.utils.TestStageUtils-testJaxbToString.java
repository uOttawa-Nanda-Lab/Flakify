@Test public void testJaxbToString() throws Exception {
  Stage s=StageUtils.getATestStage(1,2,"host1");
  String hostname=s.getHosts().get(0);
  List<ExecutionCommandWrapper> wrappers=s.getExecutionCommands(hostname);
  for (  ExecutionCommandWrapper wrapper : wrappers) {
    LOG.info("Command is " + StageUtils.jaxbToString(wrapper.getExecutionCommand()));
  }
  assertEquals(StageUtils.getActionId(1,2),s.getActionId());
}
