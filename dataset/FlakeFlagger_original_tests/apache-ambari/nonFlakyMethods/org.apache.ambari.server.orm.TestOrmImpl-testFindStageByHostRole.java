@Test public void testFindStageByHostRole(){
  injector.getInstance(OrmTestHelper.class).createStageCommands();
  HostRoleCommandDAO hostRoleCommandDAO=injector.getInstance(HostRoleCommandDAO.class);
  List<HostRoleCommandEntity> list=hostRoleCommandDAO.findByHostRole("test_host1",0L,0L,Role.DATANODE);
  assertEquals(1,list.size());
}
