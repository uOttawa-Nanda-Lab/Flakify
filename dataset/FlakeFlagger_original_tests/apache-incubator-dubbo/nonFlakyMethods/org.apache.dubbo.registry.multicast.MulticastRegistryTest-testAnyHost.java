/** 
 * Test method for  {@link org.apache.dubbo.registry.multicast.MulticastRegistry#MulticastRegistry(URL)}.
 */
@Test(expected=IllegalStateException.class) public void testAnyHost(){
  URL errorUrl=URL.valueOf("multicast://0.0.0.0/");
  new MulticastRegistry(errorUrl);
}
