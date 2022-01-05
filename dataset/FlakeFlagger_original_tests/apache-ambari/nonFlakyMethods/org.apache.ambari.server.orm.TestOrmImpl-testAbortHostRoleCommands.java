@Test public void testAbortHostRoleCommands(){
  injector.getInstance(OrmTestHelper.class).createStageCommands();
  HostRoleCommandDAO hostRoleCommandDAO=injector.getInstance(HostRoleCommandDAO.class);
  int result=hostRoleCommandDAO.updateStatusByRequestId(0L,HostRoleStatus.ABORTED,Arrays.asList(HostRoleStatus.QUEUED,HostRoleStatus.IN_PROGRESS,HostRoleStatus.PENDING));
  assertEquals(2,result);
}
