@Test public void testSortedCommands(){
  injector.getInstance(OrmTestHelper.class).createStageCommands();
  HostRoleCommandDAO hostRoleCommandDAO=injector.getInstance(HostRoleCommandDAO.class);
  HostDAO hostDAO=injector.getInstance(HostDAO.class);
  StageDAO stageDAO=injector.getInstance(StageDAO.class);
  List<HostRoleCommandEntity> list=hostRoleCommandDAO.findSortedCommandsByStageAndHost(stageDAO.findByActionId("0-0"),hostDAO.findByName("test_host1"));
  log.info("command '{}' - taskId '{}' ",list.get(0).getRoleCommand(),list.get(0).getTaskId());
  log.info("command '{}' - taskId '{}'",list.get(1).getRoleCommand(),list.get(1).getTaskId());
  assertTrue(list.get(0).getTaskId() < list.get(1).getTaskId());
}
