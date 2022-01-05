@Test public void testDubbo1UrlWithGenericInvocation(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  List<URL> serviceUrls=new ArrayList<URL>();
  URL serviceURL=SERVICEURL_DUBBO_NOPATH.addParameter("methods","getXXX1,getXXX2,getXXX3");
  serviceUrls.add(serviceURL);
  registryDirectory.notify(serviceUrls);
  invocation=new RpcInvocation(Constants.$INVOKE,new Class[]{String.class,String[].class,Object[].class},new Object[]{"getXXX1","",new Object[]{}});
  List<Invoker> invokers=registryDirectory.list(invocation);
  Assert.assertEquals(1,invokers.size());
  Assert.assertEquals(serviceURL.setPath(service).addParameters("check","false","interface",DemoService.class.getName()),invokers.get(0).getUrl());
}
