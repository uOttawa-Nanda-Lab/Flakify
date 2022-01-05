/** 
 * Test method for  {@link org.apache.dubbo.registry.multicast.MulticastRegistry#unregister(URL)}.
 */
@Test public void testUnregister(){
  Set<URL> registered;
  registry.register(serviceUrl);
  registered=registry.getRegistered();
  assertTrue(registered.contains(serviceUrl));
  registered=registry.getRegistered();
  registry.unregister(serviceUrl);
  assertFalse(registered.contains(serviceUrl));
}
