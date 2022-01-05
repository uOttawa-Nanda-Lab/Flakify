@Test public void testUsingDefaultConfig() throws Exception {
  remote.setRemoteHost(InetAddress.getLocalHost().getHostAddress());
  remote.setPort(6000);
  remote.start();
  assertNotNull(remote.getSocketFactory());
}
