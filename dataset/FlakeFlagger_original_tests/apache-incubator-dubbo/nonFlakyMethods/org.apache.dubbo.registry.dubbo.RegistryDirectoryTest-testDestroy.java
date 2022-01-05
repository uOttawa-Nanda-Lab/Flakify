/** 
 * When destroying, RegistryDirectory should: 1. be disconnected from Registry 2. destroy all invokers
 */
@Test public void testDestroy(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  List<URL> serviceUrls=new ArrayList<URL>();
  serviceUrls.add(SERVICEURL.addParameter("methods","getXXX1"));
  serviceUrls.add(SERVICEURL2.addParameter("methods","getXXX1,getXXX2"));
  serviceUrls.add(SERVICEURL3.addParameter("methods","getXXX1,getXXX2,getXXX3"));
  registryDirectory.notify(serviceUrls);
  List<Invoker> invokers=registryDirectory.list(invocation);
  Assert.assertEquals(true,registryDirectory.isAvailable());
  Assert.assertEquals(true,invokers.get(0).isAvailable());
  registryDirectory.destroy();
  Assert.assertEquals(false,registryDirectory.isAvailable());
  Assert.assertEquals(false,invokers.get(0).isAvailable());
  registryDirectory.destroy();
  Map<String,List<Invoker<RegistryDirectoryTest>>> methodInvokerMap=registryDirectory.getMethodInvokerMap();
  Map<String,Invoker<RegistryDirectoryTest>> urlInvokerMap=registryDirectory.getUrlInvokerMap();
  Assert.assertTrue(methodInvokerMap == null);
  Assert.assertEquals(0,urlInvokerMap.size());
  RpcInvocation inv=new RpcInvocation();
  try {
    registryDirectory.list(inv);
    fail();
  }
 catch (  RpcException e) {
    Assert.assertTrue(e.getMessage().contains("already destroyed"));
  }
}
