/** 
 * Test method for  {@link MulticastRegistry#isAvailable()}
 */
@Test public void testAvailability(){
  int port=NetUtils.getAvailablePort();
  MulticastRegistry registry=new MulticastRegistry(URL.valueOf("multicast://224.5.6.8:" + port));
  assertTrue(registry.isAvailable());
}
