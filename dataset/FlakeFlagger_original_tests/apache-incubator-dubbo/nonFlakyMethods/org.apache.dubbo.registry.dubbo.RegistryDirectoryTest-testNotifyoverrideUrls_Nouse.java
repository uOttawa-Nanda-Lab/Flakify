/** 
 * Test whether the override rule have a high priority Scene: the rules of the push are the same as the parameters of the provider Expectation: no need to be re-referenced
 */
@Test public void testNotifyoverrideUrls_Nouse(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  invocation=new RpcInvocation();
  List<URL> durls=new ArrayList<URL>();
  durls.add(SERVICEURL.addParameter("timeout","1"));
  durls.add(SERVICEURL2.addParameter("timeout","1").addParameter("connections","5"));
  registryDirectory.notify(durls);
  List<Invoker<?>> invokers=registryDirectory.list(invocation);
  Assert.assertEquals(2,invokers.size());
  Invoker<?> a1Invoker=invokers.get(0);
  Invoker<?> b1Invoker=invokers.get(1);
  durls=new ArrayList<URL>();
  durls.add(URL.valueOf("override://0.0.0.0?timeout=1&connections=5"));
  registryDirectory.notify(durls);
  Assert.assertEquals(true,registryDirectory.isAvailable());
  invokers=registryDirectory.list(invocation);
  Assert.assertEquals(2,invokers.size());
  Invoker<?> a2Invoker=invokers.get(0);
  Invoker<?> b2Invoker=invokers.get(1);
  Assert.assertFalse("object not same",a1Invoker == a2Invoker);
  Assert.assertTrue("object same",b1Invoker == b2Invoker);
}
