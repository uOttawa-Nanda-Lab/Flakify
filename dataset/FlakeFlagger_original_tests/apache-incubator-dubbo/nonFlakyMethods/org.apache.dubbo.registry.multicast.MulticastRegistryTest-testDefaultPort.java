/** 
 * Test method for  {@link org.apache.dubbo.registry.multicast.MulticastRegistry#MulticastRegistry(URL)}
 */
@Test public void testDefaultPort(){
  MulticastRegistry multicastRegistry=new MulticastRegistry(URL.valueOf("multicast://224.5.6.7"));
  try {
    MulticastSocket multicastSocket=multicastRegistry.getMulticastSocket();
    Assert.assertEquals(1234,multicastSocket.getLocalPort());
  }
  finally {
    multicastRegistry.destroy();
  }
}
