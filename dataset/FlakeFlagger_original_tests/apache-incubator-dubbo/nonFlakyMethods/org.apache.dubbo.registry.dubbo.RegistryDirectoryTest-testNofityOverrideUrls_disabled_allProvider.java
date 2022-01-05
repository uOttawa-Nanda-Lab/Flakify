/** 
 * Test override disables all service providers through enable=false Expectation: all service providers can not be disabled through override.
 */
@Test public void testNofityOverrideUrls_disabled_allProvider(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  invocation=new RpcInvocation();
  List<URL> durls=new ArrayList<URL>();
  durls.add(SERVICEURL.setHost("10.20.30.140"));
  durls.add(SERVICEURL.setHost("10.20.30.141"));
  registryDirectory.notify(durls);
  durls=new ArrayList<URL>();
  durls.add(URL.valueOf("override://0.0.0.0?" + Constants.ENABLED_KEY + "=false"));
  registryDirectory.notify(durls);
  List<Invoker<?>> invokers=registryDirectory.list(invocation);
  Assert.assertEquals(2,invokers.size());
}
