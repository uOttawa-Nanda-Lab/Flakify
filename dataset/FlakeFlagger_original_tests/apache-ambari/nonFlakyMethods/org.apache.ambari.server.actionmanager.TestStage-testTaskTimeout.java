@Test public void testTaskTimeout(){
  Stage s=StageUtils.getATestStage(1,1,"h1");
  s.addHostRoleExecutionCommand("h1",Role.DATANODE,RoleCommand.INSTALL,null,"c1","HDFS");
  s.addHostRoleExecutionCommand("h1",Role.HBASE_MASTER,RoleCommand.INSTALL,null,"c1","HBASE");
  assertEquals(3 * 60000,s.getTaskTimeout());
}
