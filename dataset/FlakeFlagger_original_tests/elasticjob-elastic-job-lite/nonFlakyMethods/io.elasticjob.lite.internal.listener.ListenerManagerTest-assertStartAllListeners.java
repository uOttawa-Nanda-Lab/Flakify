@Test public void assertStartAllListeners(){
  listenerManager.startAllListeners();
  verify(electionListenerManager).start();
  verify(shardingListenerManager).start();
  verify(failoverListenerManager).start();
  verify(monitorExecutionListenerManager).start();
  verify(shutdownListenerManager).start();
  verify(rescheduleListenerManager).start();
  verify(guaranteeListenerManager).start();
  verify(jobNodeStorage).addConnectionStateListener(regCenterConnectionStateListener);
}
