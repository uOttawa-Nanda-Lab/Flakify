@Test public void testLastRequestId(){
  injector.getInstance(OrmTestHelper.class).createStageCommands();
  ClusterDAO clusterDAO=injector.getInstance(ClusterDAO.class);
  StageDAO stageDAO=injector.getInstance(StageDAO.class);
  StageEntity stageEntity=new StageEntity();
  stageEntity.setCluster(clusterDAO.findByName("test_cluster1"));
  stageEntity.setRequestId(0L);
  stageEntity.setStageId(1L);
  stageDAO.create(stageEntity);
  StageEntity stageEntity2=new StageEntity();
  stageEntity2.setCluster(clusterDAO.findByName("test_cluster1"));
  stageEntity2.setRequestId(0L);
  stageEntity2.setStageId(2L);
  stageDAO.create(stageEntity2);
  assertEquals(0L,stageDAO.getLastRequestId());
}
