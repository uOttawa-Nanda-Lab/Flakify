/** 
 * Test override disables a specified service provider through enable=false It is expected that a specified service provider can be disable.
 */
@Test public void testNofity_To_Decrease_provider(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  invocation=new RpcInvocation();
  List<URL> durls=new ArrayList<URL>();
  durls.add(SERVICEURL.setHost("10.20.30.140"));
  durls.add(SERVICEURL.setHost("10.20.30.141"));
  registryDirectory.notify(durls);
  List<Invoker<?>> invokers=registryDirectory.list(invocation);
  Assert.assertEquals(2,invokers.size());
  durls=new ArrayList<URL>();
  durls.add(SERVICEURL.setHost("10.20.30.140"));
  registryDirectory.notify(durls);
  List<Invoker<?>> invokers2=registryDirectory.list(invocation);
  Assert.assertEquals(1,invokers2.size());
  Assert.assertEquals("10.20.30.140",invokers.get(0).getUrl().getHost());
  durls=new ArrayList<URL>();
  durls.add(URL.valueOf("empty://0.0.0.0?" + Constants.DISABLED_KEY + "=true&"+ Constants.CATEGORY_KEY+ "="+ Constants.CONFIGURATORS_CATEGORY));
  registryDirectory.notify(durls);
  List<Invoker<?>> invokers3=registryDirectory.list(invocation);
  Assert.assertEquals(1,invokers3.size());
}
