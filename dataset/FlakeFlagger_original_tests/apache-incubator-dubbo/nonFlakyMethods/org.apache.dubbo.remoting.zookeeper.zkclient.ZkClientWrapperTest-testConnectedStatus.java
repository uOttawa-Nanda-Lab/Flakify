@Test public void testConnectedStatus(){
  boolean connected=zkClientWrapper.isConnected();
  assertThat(connected,is(false));
  zkClientWrapper.start();
  IZkChildListener listener=mock(IZkChildListener.class);
  zkClientWrapper.subscribeChildChanges("/path",listener);
  zkClientWrapper.unsubscribeChildChanges("/path",listener);
}
