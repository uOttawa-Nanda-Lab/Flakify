/** 
 * Test mock provider distribution
 */
@Test public void testNotify_MockProviderOnly(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  List<URL> serviceUrls=new ArrayList<URL>();
  serviceUrls.add(SERVICEURL.addParameter("methods","getXXX1"));
  serviceUrls.add(SERVICEURL2.addParameter("methods","getXXX1,getXXX2"));
  serviceUrls.add(SERVICEURL.setProtocol(Constants.MOCK_PROTOCOL));
  registryDirectory.notify(serviceUrls);
  Assert.assertEquals(true,registryDirectory.isAvailable());
  invocation=new RpcInvocation();
  List invokers=registryDirectory.list(invocation);
  Assert.assertEquals(2,invokers.size());
  RpcInvocation mockinvocation=new RpcInvocation();
  mockinvocation.setAttachment(Constants.INVOCATION_NEED_MOCK,"true");
  invokers=registryDirectory.list(mockinvocation);
  Assert.assertEquals(1,invokers.size());
}
