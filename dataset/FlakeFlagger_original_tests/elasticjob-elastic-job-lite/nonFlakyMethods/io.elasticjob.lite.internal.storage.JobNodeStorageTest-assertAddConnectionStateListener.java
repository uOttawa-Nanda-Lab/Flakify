@Test public void assertAddConnectionStateListener(){
  CuratorFramework client=mock(CuratorFramework.class);
  @SuppressWarnings("unchecked") Listenable<ConnectionStateListener> listeners=mock(Listenable.class);
  ConnectionStateListener listener=mock(ConnectionStateListener.class);
  when(client.getConnectionStateListenable()).thenReturn(listeners);
  when(regCenter.getRawClient()).thenReturn(client);
  jobNodeStorage.addConnectionStateListener(listener);
  verify(listeners).addListener(listener);
}
