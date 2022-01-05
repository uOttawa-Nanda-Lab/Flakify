/** 
 * Test the simultaneous push to clear the override and the override for a certain provider See if override can take effect
 */
@Test public void testNofityOverrideUrls_CleanNOverride(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  invocation=new RpcInvocation();
  List<URL> durls=new ArrayList<URL>();
  durls.add(SERVICEURL.setHost("10.20.30.140").addParameter("timeout","1"));
  registryDirectory.notify(durls);
  durls=new ArrayList<URL>();
  durls.add(URL.valueOf("override://0.0.0.0?timeout=3"));
  durls.add(URL.valueOf("override://0.0.0.0"));
  durls.add(URL.valueOf("override://10.20.30.140:9091?timeout=4"));
  registryDirectory.notify(durls);
  List<Invoker<?>> invokers=registryDirectory.list(invocation);
  Invoker<?> aInvoker=invokers.get(0);
  Assert.assertEquals("4",aInvoker.getUrl().getParameter("timeout"));
}
