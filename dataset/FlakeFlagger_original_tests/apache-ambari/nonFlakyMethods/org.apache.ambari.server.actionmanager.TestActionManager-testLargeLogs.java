@Test public void testLargeLogs(){
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
  String errLog=Arrays.toString(new byte[100000]);
  String outLog=Arrays.toString(new byte[110000]);
  cr.setStdErr(errLog);
  cr.setStdOut(outLog);
  cr.setExitCode(215);
  reports.add(cr);
  am.processTaskResponse(hostname,reports);
  assertEquals(215,am.getAction(requestId,stageId).getExitCode(hostname,"HBASE_MASTER"));
  assertEquals(HostRoleStatus.COMPLETED,am.getAction(requestId,stageId).getHostRoleStatus(hostname,"HBASE_MASTER"));
  assertEquals(errLog.length(),am.getAction(requestId,stageId).getHostRoleCommand(hostname,"HBASE_MASTER").getStderr().length());
  assertEquals(outLog.length(),am.getAction(requestId,stageId).getHostRoleCommand(hostname,"HBASE_MASTER").getStdout().length());
}
