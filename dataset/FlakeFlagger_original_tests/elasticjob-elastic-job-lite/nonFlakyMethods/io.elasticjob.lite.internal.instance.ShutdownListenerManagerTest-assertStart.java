@Test public void assertStart(){
  shutdownListenerManager.start();
  verify(jobNodeStorage).addDataListener(ArgumentMatchers.<TreeCacheListener>any());
}
