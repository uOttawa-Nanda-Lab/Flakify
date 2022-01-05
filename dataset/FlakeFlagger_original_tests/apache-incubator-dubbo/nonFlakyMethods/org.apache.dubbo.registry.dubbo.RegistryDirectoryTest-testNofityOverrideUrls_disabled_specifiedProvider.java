/** 
 * Test override disables a specified service provider through enable=false It is expected that a specified service provider can be disable.
 */
@Test public void testNofityOverrideUrls_disabled_specifiedProvider(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  invocation=new RpcInvocation();
  List<URL> durls=new ArrayList<URL>();
  durls.add(SERVICEURL.setHost("10.20.30.140"));
  durls.add(SERVICEURL.setHost("10.20.30.141"));
  registryDirectory.notify(durls);
  durls=new ArrayList<URL>();
  durls.add(URL.valueOf("override://10.20.30.140:9091?" + Constants.DISABLED_KEY + "=true"));
  registryDirectory.notify(durls);
  List<Invoker<?>> invokers=registryDirectory.list(invocation);
  Assert.assertEquals(1,invokers.size());
  Assert.assertEquals("10.20.30.141",invokers.get(0).getUrl().getHost());
  durls=new ArrayList<URL>();
  durls.add(URL.valueOf("empty://0.0.0.0?" + Constants.DISABLED_KEY + "=true&"+ Constants.CATEGORY_KEY+ "="+ Constants.CONFIGURATORS_CATEGORY));
  registryDirectory.notify(durls);
  List<Invoker<?>> invokers2=registryDirectory.list(invocation);
  Assert.assertEquals(2,invokers2.size());
}
