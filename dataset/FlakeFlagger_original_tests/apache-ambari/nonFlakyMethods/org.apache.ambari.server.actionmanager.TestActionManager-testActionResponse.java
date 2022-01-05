@Test public void testActionResponse(){
  ActionDBAccessor db=injector.getInstance(ActionDBAccessorImpl.class);
  ActionManager am=new ActionManager(5000,1200000,new ActionQueue(),clusters,db);
  populateActionDB(db,hostname);
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
  cr.setStdErr("ERROR");
  cr.setStdOut("OUTPUT");
  cr.setExitCode(215);
  reports.add(cr);
  am.processTaskResponse(hostname,reports);
  assertEquals(215,am.getAction(requestId,stageId).getExitCode(hostname,"HBASE_MASTER"));
  assertEquals(HostRoleStatus.COMPLETED,am.getAction(requestId,stageId).getHostRoleStatus(hostname,"HBASE_MASTER"));
  assertEquals("ERROR",am.getAction(requestId,stageId).getHostRoleCommand(hostname,"HBASE_MASTER").getStderr());
  assertEquals("OUTPUT",am.getAction(requestId,stageId).getHostRoleCommand(hostname,"HBASE_MASTER").getStdout());
}
