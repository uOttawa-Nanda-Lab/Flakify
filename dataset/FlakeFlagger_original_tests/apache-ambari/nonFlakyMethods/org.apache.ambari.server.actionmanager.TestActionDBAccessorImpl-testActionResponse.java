@Test public void testActionResponse(){
  String hostname="host1";
  populateActionDB(db,hostname,requestId,stageId);
  Stage stage=db.getAllStages(requestId).get(0);
  Assert.assertEquals(stageId,stage.getStageId());
  stage.setHostRoleStatus(hostname,"HBASE_MASTER",HostRoleStatus.QUEUED);
  db.hostRoleScheduled(stage,hostname,"HBASE_MASTER");
  List<CommandReport> reports=new ArrayList<CommandReport>();
  CommandReport cr=new CommandReport();
  cr.setTaskId(1);
  cr.setActionId(StageUtils.getActionId(requestId,stageId));
  cr.setRole("HBASE_MASTER");
  cr.setStatus("COMPLETED");
  cr.setStdErr("");
  cr.setStdOut("");
  cr.setExitCode(215);
  reports.add(cr);
  am.processTaskResponse(hostname,reports);
  assertEquals(215,am.getAction(requestId,stageId).getExitCode(hostname,"HBASE_MASTER"));
  assertEquals(HostRoleStatus.COMPLETED,am.getAction(requestId,stageId).getHostRoleStatus(hostname,"HBASE_MASTER"));
  Stage s=db.getAllStages(requestId).get(0);
  assertEquals(HostRoleStatus.COMPLETED,s.getHostRoleStatus(hostname,"HBASE_MASTER"));
}
