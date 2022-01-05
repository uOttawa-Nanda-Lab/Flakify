@Test public void testStartStop() throws Exception {
  receiver.setRemoteHost(InetAddress.getLocalHost().getHostName());
  receiver.setPort(6000);
  receiver.setAcceptConnectionTimeout(DELAY / 2);
  receiver.start();
  assertTrue(receiver.isStarted());
  receiver.awaitConnectorCreated(DELAY);
  receiver.stop();
  assertFalse(receiver.isStarted());
}
