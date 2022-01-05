/** 
 * Test cleanup override rules, and sent remove rules and other override rules Whether the test can be restored to the providerUrl when it is pushed
 */
@Test public void testNofityOverrideUrls_Clean1(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  invocation=new RpcInvocation();
  List<URL> durls=new ArrayList<URL>();
  durls.add(SERVICEURL.setHost("10.20.30.140").addParameter("timeout","1"));
  registryDirectory.notify(durls);
  durls=new ArrayList<URL>();
  durls.add(URL.valueOf("override://0.0.0.0?timeout=1000"));
  registryDirectory.notify(durls);
  durls=new ArrayList<URL>();
  durls.add(URL.valueOf("override://0.0.0.0?timeout=3"));
  durls.add(URL.valueOf("override://0.0.0.0"));
  registryDirectory.notify(durls);
  List<Invoker<?>> invokers=registryDirectory.list(invocation);
  Invoker<?> aInvoker=invokers.get(0);
  Assert.assertEquals("1",aInvoker.getUrl().getParameter("timeout"));
}
