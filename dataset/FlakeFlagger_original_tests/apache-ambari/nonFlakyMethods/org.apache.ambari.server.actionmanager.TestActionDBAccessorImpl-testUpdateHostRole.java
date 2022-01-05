@Test public void testUpdateHostRole() throws Exception {
  populateActionDB(db,hostName,requestId,stageId);
  StringBuilder sb=new StringBuilder();
  for (int i=0; i < 50000; i++) {
    sb.append("1234567890");
  }
  String largeString=sb.toString();
  CommandReport commandReport=new CommandReport();
  commandReport.setStatus(HostRoleStatus.COMPLETED.toString());
  commandReport.setStdOut(largeString);
  commandReport.setStdErr(largeString);
  commandReport.setExitCode(123);
  db.updateHostRoleState(hostName,requestId,stageId,Role.HBASE_MASTER.toString(),commandReport);
  List<HostRoleCommandEntity> commandEntities=hostRoleCommandDAO.findByHostRole(hostName,requestId,stageId,Role.HBASE_MASTER);
  assertEquals(1,commandEntities.size());
  HostRoleCommandEntity commandEntity=commandEntities.get(0);
  HostRoleCommand command=db.getTask(commandEntity.getTaskId());
  assertNotNull(command);
  assertEquals(largeString,command.getStdout());
}
