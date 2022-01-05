/** 
 * Test override rules for a certain provider
 */
@Test public void testNofityOverrideUrls_Provider(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  invocation=new RpcInvocation();
  List<URL> durls=new ArrayList<URL>();
  durls.add(SERVICEURL.setHost("10.20.30.140").addParameter("timeout","1").addParameter(Constants.SIDE_KEY,Constants.CONSUMER_SIDE));
  durls.add(SERVICEURL2.setHost("10.20.30.141").addParameter("timeout","2").addParameter(Constants.SIDE_KEY,Constants.CONSUMER_SIDE));
  registryDirectory.notify(durls);
  durls=new ArrayList<URL>();
  durls.add(URL.valueOf("override://0.0.0.0?timeout=3"));
  durls.add(URL.valueOf("override://10.20.30.141:9092?timeout=4"));
  registryDirectory.notify(durls);
  List<Invoker<?>> invokers=registryDirectory.list(invocation);
  Invoker<?> aInvoker=invokers.get(0);
  Invoker<?> bInvoker=invokers.get(1);
  Assert.assertEquals("3",aInvoker.getUrl().getParameter("timeout"));
  Assert.assertEquals("4",bInvoker.getUrl().getParameter("timeout"));
}
