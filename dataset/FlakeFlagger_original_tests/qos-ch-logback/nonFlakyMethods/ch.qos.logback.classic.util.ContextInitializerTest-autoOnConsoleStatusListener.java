@Test public void autoOnConsoleStatusListener() throws JoranException {
  System.setProperty(ContextInitializer.STATUS_LISTENER_CLASS,ContextInitializer.SYSOUT);
  List<StatusListener> sll=loggerContext.getStatusManager().getCopyOfStatusListenerList();
  assertEquals(0,sll.size());
  doAutoConfigFromSystemProperties(ClassicTestConstants.INPUT_PREFIX + "autoConfig.xml");
  sll=loggerContext.getStatusManager().getCopyOfStatusListenerList();
  assertTrue(sll.size() + " should be 1",sll.size() == 1);
}
