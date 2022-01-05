/** 
 * Test method for  {@link org.apache.dubbo.registry.multicast.MulticastRegistry#MulticastRegistry(URL)}.
 */
@Test public void testGetCustomPort(){
  int port=NetUtils.getAvailablePort();
  URL customPortUrl=URL.valueOf("multicast://239.255.255.255:" + port);
  MulticastRegistry multicastRegistry=new MulticastRegistry(customPortUrl);
  assertThat(multicastRegistry.getUrl().getPort(),is(port));
}
