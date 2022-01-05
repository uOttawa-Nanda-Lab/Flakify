@Test public void testHostRoleScheduled() throws InterruptedException {
  populateActionDB(db,hostName,requestId,stageId);
  Stage stage=db.getAction(StageUtils.getActionId(requestId,stageId));
  assertEquals(HostRoleStatus.PENDING,stage.getHostRoleStatus(hostName,Role.HBASE_MASTER.toString()));
  List<HostRoleCommandEntity> entities=hostRoleCommandDAO.findByHostRole(hostName,requestId,stageId,Role.HBASE_MASTER);
  assertEquals(HostRoleStatus.PENDING,entities.get(0).getStatus());
  stage.setHostRoleStatus(hostName,Role.HBASE_MASTER.toString(),HostRoleStatus.QUEUED);
  entities=hostRoleCommandDAO.findByHostRole(hostName,requestId,stageId,Role.HBASE_MASTER);
  assertEquals(HostRoleStatus.QUEUED,stage.getHostRoleStatus(hostName,Role.HBASE_MASTER.toString()));
  assertEquals(HostRoleStatus.PENDING,entities.get(0).getStatus());
  db.hostRoleScheduled(stage,hostName,Role.HBASE_MASTER.toString());
  entities=hostRoleCommandDAO.findByHostRole(hostName,requestId,stageId,Role.HBASE_MASTER);
  assertEquals(HostRoleStatus.QUEUED,entities.get(0).getStatus());
  Thread thread=new Thread(){
    @Override public void run(){
      Stage stage1=db.getAction("23-31");
      stage1.setHostRoleStatus(hostName,Role.HBASE_MASTER.toString(),HostRoleStatus.COMPLETED);
      db.hostRoleScheduled(stage1,hostName,Role.HBASE_MASTER.toString());
    }
  }
;
  thread.start();
  thread.join();
  entities=hostRoleCommandDAO.findByHostRole(hostName,requestId,stageId,Role.HBASE_MASTER);
  assertEquals("Concurrent update failed",HostRoleStatus.COMPLETED,entities.get(0).getStatus());
}
