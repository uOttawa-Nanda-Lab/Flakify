@Test public void assertStart(){
  failoverListenerManager.start();
  verify(jobNodeStorage,times(2)).addDataListener(ArgumentMatchers.<AbstractJobListener>any());
}
