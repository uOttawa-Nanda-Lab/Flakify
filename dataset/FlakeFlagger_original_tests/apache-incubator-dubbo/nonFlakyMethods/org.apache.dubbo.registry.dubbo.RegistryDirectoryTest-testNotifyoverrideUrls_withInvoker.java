/** 
 * Test whether the override rule have a high priority Scene: push override rules with invoker
 */
@Test public void testNotifyoverrideUrls_withInvoker(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  List<URL> durls=new ArrayList<URL>();
  durls.add(SERVICEURL.addParameter("timeout","1000"));
  durls.add(SERVICEURL2.addParameter("timeout","1000").addParameter("connections","10"));
  durls.add(URL.valueOf("override://0.0.0.0?timeout=1&connections=5"));
  registryDirectory.notify(durls);
  Assert.assertEquals(true,registryDirectory.isAvailable());
  invocation=new RpcInvocation();
  List<Invoker<?>> invokers=registryDirectory.list(invocation);
  Assert.assertEquals(2,invokers.size());
  Assert.assertEquals("override rute must be first priority","1",invokers.get(0).getUrl().getParameter("timeout"));
  Assert.assertEquals("override rute must be first priority","5",invokers.get(0).getUrl().getParameter("connections"));
}
