@Test public void testServerSlowToAcceptConnection() throws Exception {
  receiver.setRemoteHost(InetAddress.getLocalHost().getHostName());
  receiver.setPort(6000);
  receiver.setAcceptConnectionTimeout(DELAY / 4);
  receiver.start();
  assertTrue(receiver.awaitConnectorCreated(DELAY / 2));
}
