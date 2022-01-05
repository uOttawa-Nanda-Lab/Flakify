/** 
 * Empty notify cause forbidden, non-empty notify cancels forbidden state
 */
@Test public void testEmptyNotifyCauseForbidden(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  List invokers=null;
  List<URL> serviceUrls=new ArrayList<URL>();
  registryDirectory.notify(serviceUrls);
  RpcInvocation inv=new RpcInvocation();
  try {
    invokers=registryDirectory.list(inv);
  }
 catch (  RpcException e) {
    Assert.assertEquals(RpcException.FORBIDDEN_EXCEPTION,e.getCode());
    Assert.assertEquals(false,registryDirectory.isAvailable());
  }
  serviceUrls.add(SERVICEURL.addParameter("methods","getXXX1"));
  serviceUrls.add(SERVICEURL2.addParameter("methods","getXXX1,getXXX2"));
  serviceUrls.add(SERVICEURL3.addParameter("methods","getXXX1,getXXX2,getXXX3"));
  registryDirectory.notify(serviceUrls);
  inv.setMethodName("getXXX2");
  invokers=registryDirectory.list(inv);
  Assert.assertEquals(true,registryDirectory.isAvailable());
  Assert.assertEquals(2,invokers.size());
}
