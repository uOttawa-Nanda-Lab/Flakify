@Test public void testFindHostsByStage(){
  injector.getInstance(OrmTestHelper.class).createStageCommands();
  HostDAO hostDAO=injector.getInstance(HostDAO.class);
  StageDAO stageDAO=injector.getInstance(StageDAO.class);
  StageEntity stageEntity=stageDAO.findByActionId("0-0");
  log.info("StageEntity {} {}" + stageEntity.getRequestId() + " "+ stageEntity.getStageId());
  List<HostEntity> hosts=hostDAO.findByStage(stageEntity);
  assertEquals(2,hosts.size());
}
