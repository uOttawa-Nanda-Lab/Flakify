@Test public void testGetServerSocketFactory() throws Exception {
  ServerSocketFactory socketFactory=receiver.getServerSocketFactory();
  assertNotNull(socketFactory);
  assertTrue(ssl.isContextCreated());
  assertTrue(parameters.isContextInjected());
}
