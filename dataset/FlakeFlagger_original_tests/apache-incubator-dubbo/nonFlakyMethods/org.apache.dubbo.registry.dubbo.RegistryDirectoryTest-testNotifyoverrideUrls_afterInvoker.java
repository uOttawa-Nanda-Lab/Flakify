/** 
 * Test whether the override rule have a high priority Scene: first push override , then push invoker
 */
@Test public void testNotifyoverrideUrls_afterInvoker(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  List<URL> serviceUrls=new ArrayList<URL>();
  serviceUrls.add(SERVICEURL.addParameter("timeout","1000"));
  serviceUrls.add(SERVICEURL2.addParameter("timeout","1000").addParameter("connections","10"));
  registryDirectory.notify(serviceUrls);
  Assert.assertEquals(true,registryDirectory.isAvailable());
  List<URL> overrideUrls=new ArrayList<URL>();
  overrideUrls.add(URL.valueOf("override://0.0.0.0?timeout=1&connections=5"));
  registryDirectory.notify(overrideUrls);
  invocation=new RpcInvocation();
  List<Invoker<?>> invokers=registryDirectory.list(invocation);
  Assert.assertEquals(2,invokers.size());
  Assert.assertEquals("override rute must be first priority","1",invokers.get(0).getUrl().getParameter("timeout"));
  Assert.assertEquals("override rute must be first priority","5",invokers.get(0).getUrl().getParameter("connections"));
}
