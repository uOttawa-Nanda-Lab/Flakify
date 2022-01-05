@Test public void assertRemoveLeader(){
  leaderService.removeLeader();
  verify(jobNodeStorage).removeJobNodeIfExisted("leader/election/instance");
}
