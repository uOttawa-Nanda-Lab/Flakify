@Test public void assertStart(){
  electionListenerManager.start();
  verify(jobNodeStorage,times(2)).addDataListener(ArgumentMatchers.<ElectionListenerManager.LeaderElectionJobListener>any());
}
