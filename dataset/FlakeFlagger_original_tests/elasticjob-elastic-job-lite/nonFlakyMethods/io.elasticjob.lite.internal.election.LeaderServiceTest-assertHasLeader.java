@Test public void assertHasLeader(){
  when(jobNodeStorage.isJobNodeExisted("leader/election/instance")).thenReturn(true);
  assertTrue(leaderService.hasLeader());
}
