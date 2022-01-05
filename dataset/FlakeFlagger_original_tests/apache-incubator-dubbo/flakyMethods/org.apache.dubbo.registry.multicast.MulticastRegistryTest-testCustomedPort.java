/** 
 * Test method for  {@link org.apache.dubbo.registry.multicast.MulticastRegistry#MulticastRegistry(URL)}
 */
@Test public void testCustomedPort(){
  int port=NetUtils.getAvailablePort();
  MulticastRegistry multicastRegistry=new MulticastRegistry(URL.valueOf("multicast://224.5.6.7:" + port));
  try {
    MulticastSocket multicastSocket=multicastRegistry.getMulticastSocket();
    assertEquals(port,multicastSocket.getLocalPort());
  }
  finally {
    multicastRegistry.destroy();
  }
}
