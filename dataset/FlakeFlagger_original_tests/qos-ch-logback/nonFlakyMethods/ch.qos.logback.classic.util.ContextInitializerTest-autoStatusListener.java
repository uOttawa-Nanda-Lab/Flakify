@Test public void autoStatusListener() throws JoranException {
  System.setProperty(ContextInitializer.STATUS_LISTENER_CLASS,TrivialStatusListener.class.getName());
  List<StatusListener> statusListenerList=loggerContext.getStatusManager().getCopyOfStatusListenerList();
  assertEquals(0,statusListenerList.size());
  doAutoConfigFromSystemProperties(ClassicTestConstants.INPUT_PREFIX + "autoConfig.xml");
  statusListenerList=loggerContext.getStatusManager().getCopyOfStatusListenerList();
  assertTrue(statusListenerList.size() + " should be 1",statusListenerList.size() == 1);
  TrivialStatusListener tsl=(TrivialStatusListener)statusListenerList.get(0);
  assertTrue("expecting at least one event in list",tsl.list.size() > 0);
}
