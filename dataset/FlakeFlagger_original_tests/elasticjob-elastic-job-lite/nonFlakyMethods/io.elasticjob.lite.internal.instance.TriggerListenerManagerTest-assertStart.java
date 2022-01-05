@Test public void assertStart(){
  triggerListenerManager.start();
  verify(jobNodeStorage).addDataListener(ArgumentMatchers.<TreeCacheListener>any());
}
