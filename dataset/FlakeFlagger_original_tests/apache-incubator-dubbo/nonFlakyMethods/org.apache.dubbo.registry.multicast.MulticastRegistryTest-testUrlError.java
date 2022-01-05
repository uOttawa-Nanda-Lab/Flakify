/** 
 * Test method for  {@link org.apache.dubbo.registry.multicast.MulticastRegistry#MulticastRegistry(URL)}.
 */
@Test(expected=IllegalStateException.class) public void testUrlError(){
  URL errorUrl=URL.valueOf("multicast://mullticast/");
  new MulticastRegistry(errorUrl);
}
