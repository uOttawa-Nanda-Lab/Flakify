/** 
 * Test method for  {@link org.apache.dubbo.registry.multicast.MulticastRegistry#getRegistered()}.
 */
@Test public void testRegister(){
  Set<URL> registered;
  registered=registry.getRegistered();
  registered.clear();
  for (int i=0; i < 2; i++) {
    registry.register(serviceUrl);
    registered=registry.getRegistered();
    assertTrue(registered.contains(serviceUrl));
  }
  registered=registry.getRegistered();
  assertEquals(1,registered.size());
}
