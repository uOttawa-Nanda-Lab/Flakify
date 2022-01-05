@Test public void testGetATestStage(){
  Stage s=StageUtils.getATestStage(1,2,"host2");
  String hostname=s.getHosts().get(0);
  List<ExecutionCommandWrapper> wrappers=s.getExecutionCommands(hostname);
  for (  ExecutionCommandWrapper wrapper : wrappers) {
    assertEquals("cluster1",wrapper.getExecutionCommand().getClusterName());
    assertEquals(StageUtils.getActionId(1,2),wrapper.getExecutionCommand().getCommandId());
    assertEquals(hostname,wrapper.getExecutionCommand().getHostname());
  }
}
