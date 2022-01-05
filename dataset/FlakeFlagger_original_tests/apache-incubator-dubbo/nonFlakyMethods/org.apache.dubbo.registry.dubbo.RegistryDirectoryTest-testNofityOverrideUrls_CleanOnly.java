/** 
 * The test clears the override rule and only sends the override cleanup rules Whether the test can be restored to the providerUrl when it is pushed
 */
@Test public void testNofityOverrideUrls_CleanOnly(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  invocation=new RpcInvocation();
  List<URL> durls=new ArrayList<URL>();
  durls.add(SERVICEURL.setHost("10.20.30.140").addParameter("timeout","1"));
  registryDirectory.notify(durls);
  Assert.assertEquals(null,registryDirectory.getUrl().getParameter("mock"));
  durls=new ArrayList<URL>();
  durls.add(URL.valueOf("override://0.0.0.0?timeout=1000&mock=fail"));
  registryDirectory.notify(durls);
  List<Invoker<?>> invokers=registryDirectory.list(invocation);
  Invoker<?> aInvoker=invokers.get(0);
  Assert.assertEquals("1000",aInvoker.getUrl().getParameter("timeout"));
  Assert.assertEquals("fail",registryDirectory.getUrl().getParameter("mock"));
  durls=new ArrayList<URL>();
  durls.add(URL.valueOf("override://0.0.0.0/dubbo.test.api.HelloService"));
  registryDirectory.notify(durls);
  invokers=registryDirectory.list(invocation);
  aInvoker=invokers.get(0);
  Assert.assertEquals("1",aInvoker.getUrl().getParameter("timeout"));
  Assert.assertEquals(null,registryDirectory.getUrl().getParameter("mock"));
}
